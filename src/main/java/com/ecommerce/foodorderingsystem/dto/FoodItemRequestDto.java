package com.ecommerce.foodorderingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodItemRequestDto {

    private String name;

    private String description;

    private int price;

    private long menuTypeId;
}
