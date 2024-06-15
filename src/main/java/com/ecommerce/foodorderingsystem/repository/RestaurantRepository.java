package com.ecommerce.foodorderingsystem.repository;

import com.ecommerce.foodorderingsystem.model.City;
import com.ecommerce.foodorderingsystem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    public List<Restaurant> findByCity(City city);
}
