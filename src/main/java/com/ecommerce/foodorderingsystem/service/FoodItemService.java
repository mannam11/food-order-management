package com.ecommerce.foodorderingsystem.service;

import com.ecommerce.foodorderingsystem.exception.exceptions.MenuTypeNotFoundException;
import com.ecommerce.foodorderingsystem.model.FoodItem;

import java.util.List;

public interface FoodItemService {

    public void addFoodItem(String name, String description, int price, long menuTypeId) throws MenuTypeNotFoundException;

    public List<FoodItem> getAllFoodItems();

    public List<FoodItem> getByMenuTypeId(long menuTypeId);
}
