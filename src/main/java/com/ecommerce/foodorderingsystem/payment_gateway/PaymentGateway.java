package com.ecommerce.foodorderingsystem.payment_gateway;

import com.ecommerce.foodorderingsystem.model.FoodItem;
import com.stripe.exception.StripeException;

import java.util.List;
import java.util.Map;

public interface PaymentGateway {
    public String generateCheckoutPaymentLink(Map<Long,Integer> foodItemQuantity, List<FoodItem> orderedItems) throws StripeException;
}
