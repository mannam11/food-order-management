package com.ecommerce.foodorderingsystem.service.kafka;

import com.ecommerce.foodorderingsystem.dto.SignupEmailDto;
import com.ecommerce.foodorderingsystem.service.email.UserEmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaConsumer {

    private UserEmailService emailService;

    private ObjectMapper objectMapper;

    @KafkaListener(topics = "signupEmail",groupId = "user-service")
    public void sendSignupEmail(String message){

        SignupEmailDto signupEmailDto = objectMapper.convertValue(message, SignupEmailDto.class);

        emailService.sendSignupEmail(signupEmailDto.getTo(), signupEmailDto.getFullName());

    }
}
