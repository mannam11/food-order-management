package com.ecommerce.foodorderingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

    private List<FoodItemDto> foodItemDto;

    private String orderDate;

    private String orderStatus;

    private double orderTotal;

}
