package com.ecommerce.foodorderingsystem.dto;

import com.ecommerce.foodorderingsystem.model.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodItemResponseDto {

    private String name;

    private String description;

    private int price;

    private String menuType;

    public static FoodItemResponseDto fromFoodItem(FoodItem foodItem) {

        return FoodItemResponseDto.builder()
                .name(foodItem.getName())
                .description(foodItem.getDescription())
                .price(foodItem.getPrice())
                .menuType(foodItem.getMenuType().getName())
                .build();
    }

}
