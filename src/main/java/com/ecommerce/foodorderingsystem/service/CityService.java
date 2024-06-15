package com.ecommerce.foodorderingsystem.service;

import com.ecommerce.foodorderingsystem.model.City;

import java.util.List;

public interface CityService {

    public List<City> getAllCities();

    public City getCityById(long id);

    public void addCity(String name);

}
