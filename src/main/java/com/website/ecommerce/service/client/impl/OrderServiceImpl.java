package com.website.ecommerce.service.client.impl;

import com.website.ecommerce.model.CartItem;
import com.website.ecommerce.model.Order;
import com.website.ecommerce.model.OrderItem;
import com.website.ecommerce.model.User;
import com.website.ecommerce.repository.OrderRepository;
import com.website.ecommerce.response.OrderItemResponse;
import com.website.ecommerce.response.OrderResponse;
import com.website.ecommerce.service.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @Override
    public OrderResponse createOrder(Order order, List<CartItem> cartItems) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setUser(user);
        order.setStatus("Đang chờ xác thực");
        Order newOrder = orderRepository.save(order);
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = OrderItem.builder()
                    .order(newOrder)
                    .product(cartItem.getProduct())
                    .productImg(cartItem.getProduct().getImg())
                    .productName(cartItem.getProduct().getName())
                    .productPrice(cartItem.getProduct().getPrice())
                    .quantityBuy(cartItem.getQuantityBuy())
                    .totalPrice(cartItem.getQuantityBuy() * cartItem.getProduct().getPrice())
                    .build();
            OrderItemResponse newOrderItemReponse = orderItemService.createOrderItem(orderItem);
            orderItemResponses.add(newOrderItemReponse);
            cartItemService.deleteCartItem(orderItem.getId());
            cartService.updateTotalCart(orderItem.getId());
        }
        OrderResponse orderResponse = OrderResponse.builder()
                .userName(newOrder.getUser().getUsername())
                .userPhone(newOrder.getUser().getPhone())
                .userAddress(newOrder.getUser().getAddress())
                .status(newOrder.getStatus())
                .paymentMethod(newOrder.getPaymentMethod())
                .createdAt(newOrder.getCreatedAt())
                .orderItemResponses(orderItemResponses)
                .build();
        return orderResponse;
    }

    @Override
    public List<Order> getOrdersByUserId(Long id) {
        List<Order> orders= orderRepository.findByUserId(id);
        return orders;
    }
}
