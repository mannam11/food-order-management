package com.ecommerce.foodorderingsystem.controller;

import com.ecommerce.foodorderingsystem.dto.RoleDto;
import com.ecommerce.foodorderingsystem.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<String> addRole(@RequestBody RoleDto roleDto) {

        roleService.save(roleDto.getName());

        return ResponseEntity.ok("Role added successfully...");
    }
}
