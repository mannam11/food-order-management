package com.ecommerce.foodorderingsystem.service;

import com.ecommerce.foodorderingsystem.model.Role;
import com.ecommerce.foodorderingsystem.model.User;

public interface UserRolesService {

    public void addUserRoles(User user, Role role);

}
