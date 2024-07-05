package com.ecommerce.foodorderingsystem.service.impl;

import com.ecommerce.foodorderingsystem.model.City;
import com.ecommerce.foodorderingsystem.repository.CityRepository;
import com.ecommerce.foodorderingsystem.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityById(long id) {
        Optional<City> city = cityRepository.findById(id);
        return city.orElse(null);
    }

    @Override
    public void addCity(String cityName) {
        City city = City.builder()
                .name(cityName.toLowerCase())
                .build();

        cityRepository.save(city);
    }

}
