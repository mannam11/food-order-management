package com.ecommerce.foodorderingsystem.service.impl;

import com.ecommerce.foodorderingsystem.exception.exceptions.MenuTypeNotFoundException;
import com.ecommerce.foodorderingsystem.model.FoodItem;
import com.ecommerce.foodorderingsystem.model.MenuType;
import com.ecommerce.foodorderingsystem.repository.FoodItemRepository;
import com.ecommerce.foodorderingsystem.repository.MenuTypeRepository;
import com.ecommerce.foodorderingsystem.service.FoodItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepository foodItemRepository;

    private final MenuTypeRepository menuTypeRepository;

    @Override
    public void addFoodItem(String name, String description, int price, long menuTypeId) throws MenuTypeNotFoundException {

        Optional<MenuType> menuType = menuTypeRepository.findById(menuTypeId);

        if(menuType.isEmpty()){
            throw new MenuTypeNotFoundException("MenuType not found");
        }

        FoodItem foodItem = FoodItem.builder()
                .name(name)
                .description(description)
                .price(price)
                .menuType(menuType.get())
                .build();

        foodItemRepository.save(foodItem);

    }

    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    @Override
    public List<FoodItem> getByMenuTypeId(long menuTypeId) {
        return foodItemRepository.findFoodItemByMenuTypeId(menuTypeId);
    }
}
