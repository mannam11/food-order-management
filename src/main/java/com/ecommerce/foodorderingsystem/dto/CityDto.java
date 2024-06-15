package com.ecommerce.foodorderingsystem.dto;

import com.ecommerce.foodorderingsystem.model.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {

    private String name;

    public CityDto toCityDto(City city) {
        return CityDto.builder()
                .name(city.getName())
                .build();
    }
}
