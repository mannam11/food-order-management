package com.ecommerce.foodorderingsystem.service;

import com.ecommerce.foodorderingsystem.model.Role;
import com.ecommerce.foodorderingsystem.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Override
    public void save(String name) {

        Role role = Role.builder()
                .name(name.toUpperCase())
                .build();

        roleRepository.save(role);
    }
}
