package com.ecommerce.foodorderingsystem.service;

import com.ecommerce.foodorderingsystem.helper.OrderList;
import com.ecommerce.foodorderingsystem.model.Orders;
import com.stripe.exception.StripeException;
import jakarta.persistence.criteria.Order;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface OrderService {

    public String createOrder(List<OrderList> orderLists, long restaurantId,long userId) throws BadRequestException, StripeException;

    public List<Orders> getOrderListsByRestaurantId(long restaurantId) throws BadRequestException;
}
