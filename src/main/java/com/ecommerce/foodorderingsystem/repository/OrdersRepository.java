package com.ecommerce.foodorderingsystem.repository;

import com.ecommerce.foodorderingsystem.model.OrderStatus;
import com.ecommerce.foodorderingsystem.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByOrderDateAndOrderStatus(LocalDate orderDate, OrderStatus orderStatus);
}
