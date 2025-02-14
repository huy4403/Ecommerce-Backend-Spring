package com.website.ecommerce.service.client;

import com.website.ecommerce.model.OrderItem;
import com.website.ecommerce.response.OrderItemResponse;

import java.util.List;

public interface OrderItemService {
    OrderItemResponse createOrderItem(OrderItem orderItem);
}
