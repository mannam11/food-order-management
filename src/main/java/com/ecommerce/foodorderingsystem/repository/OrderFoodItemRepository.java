package com.ecommerce.foodorderingsystem.repository;

import com.ecommerce.foodorderingsystem.model.OrderFoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodItemRepository extends JpaRepository<OrderFoodItem, Long> {
}
