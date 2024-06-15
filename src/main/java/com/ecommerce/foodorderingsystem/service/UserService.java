package com.ecommerce.foodorderingsystem.service;

import com.ecommerce.foodorderingsystem.model.User;
import org.apache.coyote.BadRequestException;

public interface UserService {

    public void save(String firstName, String lastName, String email, String password,long roleId) throws BadRequestException;

    public void loginUser(String email, String password) throws BadRequestException;

    public void logoutUser(String email) throws  BadRequestException;
}
