package com.ecommerce.foodorderingsystem.controller;

import com.ecommerce.foodorderingsystem.dto.FoodItemRequestDto;
import com.ecommerce.foodorderingsystem.dto.FoodItemResponseDto;
import com.ecommerce.foodorderingsystem.exception.exceptions.MenuTypeNotFoundException;
import com.ecommerce.foodorderingsystem.model.FoodItem;
import com.ecommerce.foodorderingsystem.service.FoodItemService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/foodItem")
@AllArgsConstructor
public class FoodItemController {

    private final FoodItemService foodItemService;

    @PostMapping
    public ResponseEntity<String> addFoodItem(@RequestBody FoodItemRequestDto foodItemRequestDto) throws MenuTypeNotFoundException, BadRequestException {

        if(foodItemRequestDto.getName().trim().isEmpty() || foodItemRequestDto.getDescription().trim().isEmpty()){
            throw new BadRequestException("Name and description cannot be empty");
        }

        if(foodItemRequestDto.getPrice() <= 0){
            throw new BadRequestException("Price must be greater than 0");
        }

        if(foodItemRequestDto.getMenuTypeId() <= 0){
            throw new BadRequestException("MenuTypeId must be greater than 0");
        }

        foodItemService.addFoodItem(foodItemRequestDto.getName(), foodItemRequestDto.getDescription(), foodItemRequestDto.getPrice(), foodItemRequestDto.getMenuTypeId() );

        return new ResponseEntity<>("FoodItem added successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FoodItemResponseDto>> getAllFoodItems() {

        List<FoodItem> foodItems = foodItemService.getAllFoodItems();

        List<FoodItemResponseDto> foodItemResponseDtos = foodItems.stream()
                .map(FoodItemResponseDto::fromFoodItem)
                .toList();

        return new ResponseEntity<>(foodItemResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/{menuTypeId}")
    public ResponseEntity<List<FoodItemResponseDto>> getFoodItemByMenuTypeId(@PathVariable long menuTypeId) throws MenuTypeNotFoundException {

        if(menuTypeId <= 0){
            throw new MenuTypeNotFoundException("MenuTypeId must be greater than 0");
        }

        List<FoodItem> foodItems = foodItemService.getByMenuTypeId(menuTypeId);

        List<FoodItemResponseDto> foodItemResponseDtos = foodItems.stream()
                .map(FoodItemResponseDto::fromFoodItem)
                .toList();

        return new ResponseEntity<>(foodItemResponseDtos, HttpStatus.OK);
    }
}
