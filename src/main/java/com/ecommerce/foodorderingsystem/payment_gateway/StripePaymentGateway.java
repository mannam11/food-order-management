package com.ecommerce.foodorderingsystem.payment_gateway;

import com.ecommerce.foodorderingsystem.model.FoodItem;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class StripePaymentGateway implements PaymentGateway {

    private StripeClient stripeClient;

    @Override
    public String generateCheckoutPaymentLink(Map<Long,Integer> foodItemQuantity, List<FoodItem> orderedItems) throws StripeException {

        List<SessionCreateParams.LineItem> lineItemsList = new ArrayList<>();

        for(FoodItem foodItem : orderedItems) {

            SessionCreateParams.LineItem lineItem= SessionCreateParams.LineItem.builder()
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                    .setUnitAmount((long) (foodItem.getPrice()*100))
                                    .setCurrency("INR")
                                    .setProductData(
                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName(foodItem.getName())
                                                    .setDescription(foodItem.getDescription())
                                                    .build()
                                    )
                                    .build()
                    )
                    .setQuantity((long)foodItemQuantity.get(foodItem.getId()))
                    .build();

            lineItemsList.add(lineItem);
        }

        SessionCreateParams sessionCreateParams = SessionCreateParams.builder()
                .addAllLineItem(lineItemsList)
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("https://www.linkedin.com/")
                .setExpiresAt(System.currentTimeMillis()/1000L +(30*60))
                .setCurrency("INR")
                .build();

        Session session = stripeClient.checkout().sessions().create(sessionCreateParams);

        return session.toString();
    }


}
