package com.website.ecommerce.service;

import com.website.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.Query;

public interface CartService {
    Cart createCart(Cart cart);

    Cart updateCart(Cart cart);

    void updateTotalCart(int cartId);

    Cart getCartByUserId(int id);
}