package com.ecommerce.foodorderingsystem.controller;

import com.ecommerce.foodorderingsystem.dto.RestaurantRequestDto;
import com.ecommerce.foodorderingsystem.dto.RestaurantResponseDto;
import com.ecommerce.foodorderingsystem.exception.exceptions.CityNotFoundException;
import com.ecommerce.foodorderingsystem.model.Restaurant;
import com.ecommerce.foodorderingsystem.service.OrderService;
import com.ecommerce.foodorderingsystem.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/restaurant")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createRestaurant(@RequestBody RestaurantRequestDto restaurantDto) throws Exception {

        if(restaurantDto.getName().trim().isEmpty() || restaurantDto.getAddress().trim().isEmpty() || restaurantDto.getCityId() == null) {
            throw new BadRequestException("Restaurant name and address and city cannot be empty...!");
        }

        if(restaurantDto.getCityId() <= 0){
            throw new BadRequestException("Restaurant city id cannot be empty or 0...!");
        }

        restaurantService.addRestaurant(restaurantDto.getName(), restaurantDto.getAddress(), restaurantDto.getCityId());
        return new ResponseEntity<>("Restaurant added successfully...!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {

        List<Restaurant> restaurants = restaurantService.findAll();

        List<RestaurantResponseDto> restaurantResponseDtos = restaurants.stream()
                .map(RestaurantResponseDto::fromRestaurant)
                .toList();

        return new ResponseEntity<>(restaurantResponseDtos, HttpStatus.OK);
    }

    @GetMapping("{cityName}")
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurantsByCityName(@PathVariable String cityName) throws CityNotFoundException {

        if(cityName.trim().isEmpty()){
            throw new CityNotFoundException("City name cannot be empty...!");
        }

        List<Restaurant> restaurants = restaurantService.getAllRestaurantsByCityName(cityName);

        List<RestaurantResponseDto> restaurantResponseDtos = restaurants.stream()
                .map(RestaurantResponseDto::fromRestaurant)
                .toList();

        return new ResponseEntity<>(restaurantResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/amount")
    public ResponseEntity<Double> getTotalAmountEarnedOnDate(@RequestParam String date) {

        LocalDate expectedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return new ResponseEntity<>(orderService.getTotalEarnedAmountF(expectedDate), HttpStatus.OK);
    }

}
