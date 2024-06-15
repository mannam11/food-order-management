package com.ecommerce.foodorderingsystem.service;

import com.ecommerce.foodorderingsystem.model.MenuType;

import java.util.List;

public interface MenuTypeService {

    public void addMenuType(String menuTypeName);

    public List<MenuType> getAllMenuTypes();
}
