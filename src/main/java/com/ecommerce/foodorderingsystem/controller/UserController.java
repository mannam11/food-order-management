package com.ecommerce.foodorderingsystem.controller;

import com.ecommerce.foodorderingsystem.dto.LoginRequestDto;
import com.ecommerce.foodorderingsystem.dto.LogoutRequestDto;
import com.ecommerce.foodorderingsystem.dto.SignupRequestDto;
import com.ecommerce.foodorderingsystem.model.User;
import com.ecommerce.foodorderingsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody SignupRequestDto userRequestDto) throws BadRequestException {
        userService.save(userRequestDto.getFirstName(), userRequestDto.getLastName(), userRequestDto.getEmail(), userRequestDto.getPassword(), userRequestDto.getRoleId());
        return ResponseEntity.ok("User created");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDto loginRequestDto) throws BadRequestException {

        if(loginRequestDto.getEmail().trim().isEmpty() || loginRequestDto.getPassword().trim().isEmpty()) {
            return new ResponseEntity<>("Email or Password is missing", HttpStatus.BAD_REQUEST);
        }

        userService.loginUser(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        return ResponseEntity.ok("User logged in");
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestBody LogoutRequestDto logoutRequestDto) throws BadRequestException {
        userService.logoutUser(logoutRequestDto.getEmail());
        return ResponseEntity.ok("User logged out successfully...");
    }
}
