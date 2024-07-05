package com.ecommerce.foodorderingsystem.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupEmailDto {

    private String to;

    private String fullName;

    public SignupEmailDto(String message) {
        // Parse the JSON string from 'message' and set 'to' and 'fullName' fields
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, String> data = mapper.readValue(message, Map.class);
            this.to = data.get("to");
            this.fullName = data.get("fullName");
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }
}
