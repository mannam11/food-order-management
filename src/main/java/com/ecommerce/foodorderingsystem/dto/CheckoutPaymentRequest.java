package com.ecommerce.foodorderingsystem.dto;

import lombok.Data;

@Data
public class CheckoutPaymentRequest {

    private long quantity;

    private long price;
}
