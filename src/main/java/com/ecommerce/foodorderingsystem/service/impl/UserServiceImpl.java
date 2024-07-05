package com.ecommerce.foodorderingsystem.service.impl;

import com.ecommerce.foodorderingsystem.dto.SignupEmailDto;
import com.ecommerce.foodorderingsystem.model.Role;
import com.ecommerce.foodorderingsystem.model.User;
import com.ecommerce.foodorderingsystem.repository.UserRepository;
import com.ecommerce.foodorderingsystem.service.UserService;
import com.ecommerce.foodorderingsystem.service.kafka.KafkaProducerClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final KafkaProducerClient kafkaProducerClient;

    private final ObjectMapper objectMapper;

    @Override
    public void save(String firstName, String lastName, String email, String password) throws BadRequestException, JsonProcessingException {
        User user = User.builder()
                .firstName(firstName.toLowerCase())
                .lastName(lastName.toLowerCase())
                .email(email.toLowerCase())
                .password(password)
                .role(Role.USER)
                .build();

        userRepository.save(user);

        SignupEmailDto signupEmailDto = SignupEmailDto.builder()
                .to(user.getEmail())
                .fullName(user.getFullName())
                .build();

        kafkaProducerClient.send("signupEmail",objectMapper.writeValueAsString(signupEmailDto));
    }

    @Override
    public void loginUser(String email, String password) throws BadRequestException {

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()){
            throw new BadRequestException("Email does not exist");
        }

        if(!user.get().getPassword().equals(password)){
            throw new BadRequestException("email or password is incorrect");
        }

        user.get().setLoggedIn(true);

        userRepository.save(user.get());
    }

    @Override
    public void logoutUser(String email) throws BadRequestException {

        Optional<User> user = userRepository.findByEmail(email);

        if(!user.get().isLoggedIn()){
            throw new BadRequestException("please login");
        }

        user.get().setLoggedIn(false);

        userRepository.save(user.get());

    }

}
