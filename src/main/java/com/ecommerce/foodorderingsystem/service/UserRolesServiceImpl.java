package com.ecommerce.foodorderingsystem.service;

import com.ecommerce.foodorderingsystem.model.Role;
import com.ecommerce.foodorderingsystem.model.User;
import com.ecommerce.foodorderingsystem.model.UserRoles;
import com.ecommerce.foodorderingsystem.repository.UserRolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRolesServiceImpl implements UserRolesService {

    private final UserRolesRepository userRolesRepository;


    @Override
    public void addUserRoles(User user, Role role) {
        UserRoles userRoles = UserRoles.builder()
                .user(user)
                .role(role)
                .build();

        userRolesRepository.save(userRoles);
    }
}
