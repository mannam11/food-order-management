package com.ecommerce.foodorderingsystem.controller;

import com.ecommerce.foodorderingsystem.dto.MenuTypeDto;
import com.ecommerce.foodorderingsystem.model.MenuType;
import com.ecommerce.foodorderingsystem.service.MenuTypeService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menuType")
@AllArgsConstructor
public class MenuTypeController {

    private final MenuTypeService menuTypeService;

    @PostMapping
    public ResponseEntity<String> addMenuType(@RequestBody MenuTypeDto menuTypeDto) throws BadRequestException {
        if(menuTypeDto.getName().trim().isEmpty()){
            throw new BadRequestException("Name cannot be empty");
        }

        menuTypeService.addMenuType(menuTypeDto.getName());
        return new ResponseEntity<>("Menutype added successfully...!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MenuTypeDto>> getAllMenuTypes() {

        List<MenuType> menuTypes = menuTypeService.getAllMenuTypes();

        List<MenuTypeDto> menuTypeDtos = menuTypes.stream()
                .map(MenuTypeDto::fromMenuType)
                .toList();

        return new ResponseEntity<>(menuTypeDtos, HttpStatus.OK);
    }

}
