package com.ecommerce.foodorderingsystem.config;

import com.stripe.StripeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.api_key}")
    private String apiKey;

    @Bean
    public StripeClient getStripeClient() {
        return new StripeClient(apiKey);
    }

}
