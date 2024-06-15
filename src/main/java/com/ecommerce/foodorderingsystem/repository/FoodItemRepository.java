package com.ecommerce.foodorderingsystem.repository;

import com.ecommerce.foodorderingsystem.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    public List<FoodItem> findFoodItemByMenuTypeId(long menuTypeId);

    public List<FoodItem> findALLByIdIn(List<Long> ids);
}
