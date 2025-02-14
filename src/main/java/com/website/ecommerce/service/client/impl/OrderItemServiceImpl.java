package com.website.ecommerce.service.client.impl;

import com.website.ecommerce.model.OrderItem;
import com.website.ecommerce.repository.OrderItemRepository;
import com.website.ecommerce.response.OrderItemResponse;
import com.website.ecommerce.service.client.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItemResponse createOrderItem(OrderItem orderItems) {
        OrderItem newOrderItem = orderItemRepository.save(orderItems);
        OrderItemResponse orderItemResponse = OrderItemResponse.builder()
                .productImg(newOrderItem.getProductImg())
                .productName(newOrderItem.getProductName())
                .productPrice(newOrderItem.getProductPrice())
                .quantityBuy(newOrderItem.getQuantityBuy())
                .totalPrice(newOrderItem.getTotalPrice())
                .build();
        return orderItemResponse;
    }
}
