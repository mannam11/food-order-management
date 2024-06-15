package com.ecommerce.foodorderingsystem.repository;

import com.ecommerce.foodorderingsystem.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
