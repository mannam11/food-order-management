package com.ecommerce.foodorderingsystem.repository;

import com.ecommerce.foodorderingsystem.model.MenuType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuTypeRepository extends JpaRepository<MenuType, Long> {
}
