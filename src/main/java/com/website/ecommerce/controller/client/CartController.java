package com.website.ecommerce.controller.client;

import com.website.ecommerce.model.Cart;
import com.website.ecommerce.service.client.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/user/cart")
public class CartController {
    private CartService cartService;

    @PutMapping("update/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable("id") Long id, @RequestBody Cart cart) {
        cart.setId(id);
        Cart updateCart = cartService.updateCart(cart);
        return new ResponseEntity<>(updateCart, HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<Cart> getCartByUserId(@PathVariable("userId") Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
