package com.website.ecommerce.service.client;

import com.website.ecommerce.model.Cart;

public interface CartService {
    Cart createCart(Cart cart);

    Cart updateCart(Cart cart);

    void updateTotalCart(Long cartId);

    Cart getCartByUserId(Long id);
}
