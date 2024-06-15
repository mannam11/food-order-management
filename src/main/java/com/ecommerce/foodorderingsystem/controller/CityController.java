package com.ecommerce.foodorderingsystem.controller;

import com.ecommerce.foodorderingsystem.dto.CityDto;
import com.ecommerce.foodorderingsystem.model.City;
import com.ecommerce.foodorderingsystem.service.CityService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    public ResponseEntity<String> addCity(@RequestBody CityDto cityDto) throws BadRequestException {
        if(cityDto.getName().trim().isEmpty()){
            throw new BadRequestException("Name cannot be empty");
        }

        cityService.addCity(cityDto.getName());
        return new ResponseEntity<>("City added successfully!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllCities(){

        List<City> cities = cityService.getAllCities();

        List<String> cityNames = cities.stream().map(City::getName).toList();

        return new ResponseEntity<>(cityNames, HttpStatus.OK);
    }

}
