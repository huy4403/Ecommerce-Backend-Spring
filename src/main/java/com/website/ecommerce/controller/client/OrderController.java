package com.website.ecommerce.controller.client;

import com.website.ecommerce.model.CartItem;
import com.website.ecommerce.model.Order;
import com.website.ecommerce.response.OrderResponse;
import com.website.ecommerce.service.client.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/user/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("create")
    public ResponseEntity<?> createOrder(@ModelAttribute Order order, @RequestBody List<CartItem> carItems) {
        OrderResponse orderResponse = orderService.createOrder(order, carItems);
        return ResponseEntity.ok(orderResponse);
    }
}
