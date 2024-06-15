package com.ecommerce.foodorderingsystem.dto;

import com.ecommerce.foodorderingsystem.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantResponseDto {

    private String name;

    private String address;

    private String cityName;

    public static RestaurantResponseDto fromRestaurant(Restaurant restaurant) {

        return RestaurantResponseDto.builder()
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .cityName(restaurant.getCity().getName())
                .build();
    }
}

