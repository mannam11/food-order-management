package com.ecommerce.foodorderingsystem.controller;

import com.ecommerce.foodorderingsystem.dto.FoodItemResponseDto;
import com.ecommerce.foodorderingsystem.model.FoodItem;
import com.ecommerce.foodorderingsystem.model.MenuType;
import com.ecommerce.foodorderingsystem.service.FoodItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FoodItemControllerTest {

    @Autowired
    private FoodItemController foodItemController;

    @Autowired
    private FoodItemService foodItemService;

    @Test
    void testGetAllFoodItems() {

        MenuType menuType = new MenuType("veg");

        List<FoodItem> foodItems = new ArrayList<>();
        foodItems.add(new FoodItem(1L,"Pizza", "Delicious Pizza", 10, menuType));

        when(foodItemService.getAllFoodItems()).thenReturn(foodItems);

        ResponseEntity<List<FoodItemResponseDto>> response = foodItemController.getAllFoodItems();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

}