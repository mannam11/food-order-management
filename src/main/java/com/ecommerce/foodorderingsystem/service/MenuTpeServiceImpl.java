package com.ecommerce.foodorderingsystem.service;

import com.ecommerce.foodorderingsystem.model.MenuType;
import com.ecommerce.foodorderingsystem.repository.MenuTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuTpeServiceImpl implements MenuTypeService{

    private final MenuTypeRepository menuTypeRepository;

    @Override
    public void addMenuType(String menuTypeName) {

        MenuType menuType = MenuType.builder()
                .name(menuTypeName.toLowerCase())
                .build();

        menuTypeRepository.save(menuType);
    }

    @Override
    public List<MenuType> getAllMenuTypes() {
        return menuTypeRepository.findAll();
    }
}
