package com.ecommerce.foodorderingsystem.service;

import com.ecommerce.foodorderingsystem.model.FoodItem;
import com.ecommerce.foodorderingsystem.model.OrderFoodItem;
import com.ecommerce.foodorderingsystem.model.Orders;

public interface OrderFoodItemService {

    public void addOrderFoodItem(FoodItem foodItem, Orders order, int quantity);
}
