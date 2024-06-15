package com.ecommerce.foodorderingsystem.controller;

import com.ecommerce.foodorderingsystem.dto.FoodItemDto;
import com.ecommerce.foodorderingsystem.dto.OrderDto;
import com.ecommerce.foodorderingsystem.dto.OrderResponseDto;
import com.ecommerce.foodorderingsystem.helper.OrderList;
import com.ecommerce.foodorderingsystem.model.FoodItem;
import com.ecommerce.foodorderingsystem.model.OrderFoodItem;
import com.ecommerce.foodorderingsystem.model.Orders;
import com.ecommerce.foodorderingsystem.service.OrderService;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{restaurantId}/{userId}")
    public ResponseEntity<String> createOrder(@RequestBody List<OrderDto> orderDtos, @PathVariable long restaurantId,@PathVariable long userId) throws BadRequestException, StripeException {

        if(orderDtos.isEmpty()){
            throw new BadRequestException("Please select at least one item...");
        }

        List<OrderList> orderLists = new ArrayList<>();

        for(OrderDto orderDto : orderDtos){

            OrderList orderList = new OrderList();

            orderList.setFoodItemId(orderDto.getFoodItemId());
            orderList.setQuantity(orderDto.getQuantity());

            orderLists.add(orderList);

        }

        return new ResponseEntity<>(orderService.createOrder(orderLists,restaurantId,userId), HttpStatus.CREATED);

    }


    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<OrderResponseDto>> getAllOrdersByRestaurantId(@PathVariable long restaurantId) throws BadRequestException {

        List<Orders> orders = orderService.getOrderListsByRestaurantId(restaurantId);

        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();

        for(Orders order : orders){

            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setOrderDate(order.getOrderDate().toString());
            orderResponseDto.setOrderStatus(order.getOrderStatus().toString());
            orderResponseDto.setOrderTotal(order.getTotalAmount());

            List<OrderFoodItem> orderFoodItems = order.getOrderFoodItems();

            List<FoodItemDto> foodItemDtoList = new ArrayList<>();
            for(OrderFoodItem orderFoodItem : orderFoodItems){
                FoodItemDto foodItemDto = new FoodItemDto();
                foodItemDto.setName(orderFoodItem.getFoodItem().getName());
                foodItemDto.setQuantity(orderFoodItem.getQuantity());

                foodItemDtoList.add(foodItemDto);
            }

            orderResponseDto.setFoodItemDto(foodItemDtoList);

            orderResponseDtos.add(orderResponseDto);

        }

        return new ResponseEntity<>(orderResponseDtos,HttpStatus.OK);
    }
}
