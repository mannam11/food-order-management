package com.ecommerce.foodorderingsystem.service;

import com.ecommerce.foodorderingsystem.exception.exceptions.CityNotFoundException;
import com.ecommerce.foodorderingsystem.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    public List<Restaurant> findAll();

    public void addRestaurant(String name, String address,long id) throws CityNotFoundException;

    public List<Restaurant> getAllRestaurantsByCityName(String cityName) throws CityNotFoundException;

}
