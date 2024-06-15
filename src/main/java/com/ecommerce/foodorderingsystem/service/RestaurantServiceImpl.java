package com.ecommerce.foodorderingsystem.service;

import com.ecommerce.foodorderingsystem.dto.RestaurantResponseDto;
import com.ecommerce.foodorderingsystem.exception.exceptions.CityNotFoundException;
import com.ecommerce.foodorderingsystem.model.City;
import com.ecommerce.foodorderingsystem.model.Restaurant;
import com.ecommerce.foodorderingsystem.model.RestaurantStatus;
import com.ecommerce.foodorderingsystem.repository.CityRepository;
import com.ecommerce.foodorderingsystem.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;

    private final CityRepository cityRepository;

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public void addRestaurant(String name, String address,long id) throws CityNotFoundException {

        Optional<City> city = cityRepository.findById(id);
        if(city.isEmpty()){
            throw new CityNotFoundException("City not found with the given id");
        }

        Restaurant restaurant =Restaurant.builder()
                .name(name)
                .address(address)
                .city(city.get())
                .restaurantStatus(RestaurantStatus.TAKING_ORDERS)
                .build();

        restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurantsByCityName(String cityName) throws CityNotFoundException {

        Optional<City> city = cityRepository.findByName(cityName);

        if(city.isEmpty()){
            throw new CityNotFoundException("City not found with the given name");
        }

        return city.get().getRestaurants();
    }

}
