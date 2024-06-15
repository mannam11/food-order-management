package com.ecommerce.foodorderingsystem.service;

import com.ecommerce.foodorderingsystem.model.FoodItem;
import com.ecommerce.foodorderingsystem.model.OrderFoodItem;
import com.ecommerce.foodorderingsystem.model.Orders;
import com.ecommerce.foodorderingsystem.repository.OrderFoodItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderFoodItemServiceImpl implements OrderFoodItemService{

    private final OrderFoodItemRepository orderFoodItemRepository;

    @Override
    public void addOrderFoodItem(FoodItem foodItem, Orders order, int quantity) {

        OrderFoodItem orderFoodItem = OrderFoodItem.builder()
                .foodItem(foodItem)
                .quantity(quantity)
                .order(order)
                .build();

        orderFoodItemRepository.save(orderFoodItem);

    }
}
