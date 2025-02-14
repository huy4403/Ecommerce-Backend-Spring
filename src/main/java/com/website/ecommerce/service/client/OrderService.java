package com.website.ecommerce.service.client;

import com.website.ecommerce.model.CartItem;
import com.website.ecommerce.model.Order;
import com.website.ecommerce.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(Order order, List<CartItem> cartItems);

    List<Order> getOrdersByUserId(Long id);
}
