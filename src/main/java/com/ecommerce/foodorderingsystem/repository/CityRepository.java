package com.ecommerce.foodorderingsystem.repository;

import com.ecommerce.foodorderingsystem.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    public Optional<City> findByName(String name);
}
