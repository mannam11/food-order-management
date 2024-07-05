package com.ecommerce.foodorderingsystem.payment_gateway;

import com.ecommerce.foodorderingsystem.model.FoodItem;
import com.ecommerce.foodorderingsystem.model.User;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentGateway paymentGateway;

    public String checkoutPayment(Map<Long,Integer> foodItemQuantity, List<FoodItem> orderedItems, User orderedUser) throws StripeException {
        return paymentGateway.generateCheckoutPaymentLink(foodItemQuantity, orderedItems,orderedUser);
    }
}
