package com.ecommerce.foodorderingsystem.service.impl;

import com.ecommerce.foodorderingsystem.helper.OrderList;
import com.ecommerce.foodorderingsystem.model.*;
import com.ecommerce.foodorderingsystem.payment_gateway.PaymentService;
import com.ecommerce.foodorderingsystem.repository.FoodItemRepository;
import com.ecommerce.foodorderingsystem.repository.OrdersRepository;
import com.ecommerce.foodorderingsystem.repository.RestaurantRepository;
import com.ecommerce.foodorderingsystem.repository.UserRepository;
import com.ecommerce.foodorderingsystem.service.OrderFoodItemService;
import com.ecommerce.foodorderingsystem.service.OrderService;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;

    private final FoodItemRepository foodItemRepository;

    private final OrderFoodItemService orderFoodItemService;

    private final RestaurantRepository restaurantRepository;

    private final UserRepository userRepository;

    private final PaymentService paymentService;

    @Override
    public String createOrder(List<OrderList> orderLists, long restaurantId,long userId) throws BadRequestException, StripeException {

        Orders order = new Orders();

        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new BadRequestException("User not found");
        }

        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(restaurant.isEmpty()){
            throw new BadRequestException("Restaurant not found...");
        }

        if(restaurant.get().getRestaurantStatus().equals(RestaurantStatus.CLOSED)){
            throw new BadRequestException("Restaurant is closed...");
        }

        double totalOrderAmount = 0;
        long totalQuantity = 0;

        Map<Long,Integer> orderItemsQuantity = new HashMap<>();

        List<Long> foodItemIds = new ArrayList<>();

        for(OrderList orderList : orderLists){
            orderItemsQuantity.put(orderList.getFoodItemId(), orderList.getQuantity());
            totalQuantity+= orderList.getQuantity();
            foodItemIds.add(orderList.getFoodItemId());
        }

        List<FoodItem> foodItems = foodItemRepository.findALLByIdIn(foodItemIds);
        if(foodItemIds.size() != foodItems.size()){
            throw new BadRequestException("Food items not found...");
        }

        for(FoodItem foodItem : foodItems){

            totalOrderAmount += foodItem.getPrice()*orderItemsQuantity.get(foodItem.getId());

            orderFoodItemService.addOrderFoodItem(foodItem,order,orderItemsQuantity.get(foodItem.getId()));
        }

        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setRestaurant(restaurant.get());
        order.setTotalQuantity(totalQuantity);
        order.setTotalAmount(totalOrderAmount);
        order.setUser(user.get());

        ordersRepository.save(order);

        return paymentService.checkoutPayment(orderItemsQuantity,foodItems,user.get());

    }

    @Override
    public List<Orders> getOrderListsByRestaurantId(long restaurantId) throws BadRequestException {

        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if(restaurant.isEmpty()){
            throw new BadRequestException("Restaurant not found...");
        }

        return restaurant.get().getOrders();
    }

    @Override
    public double getTotalEarnedAmountF(LocalDate date) {

        List<Orders> orderLists = ordersRepository.findByOrderDateAndOrderStatus(date,OrderStatus.DELIVERED);

        double totalEarnedAmount = 0;

        for(Orders order : orderLists){

            totalEarnedAmount+=order.getTotalAmount();

        }

        return totalEarnedAmount;

    }
}
