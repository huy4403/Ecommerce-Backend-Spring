package com.website.ecommerce.service;

import com.website.ecommerce.model.Cart;
import com.website.ecommerce.model.CartItem;

import java.util.List;

public interface CartItemService {

    CartItem addCartItem(CartItem cartItem);

    CartItem updateCartItem(CartItem cartItem);

    void deleteCartItem(Long id);

    List<CartItem> getCartItemsByCartId(Long cartId);

    CartItem findCartItemById(Long id);

    int quantityByCartIdAndProductId(Long cartId, Long productId);

    CartItem updateCartItemQuantityBuy(CartItem cartItem);

    CartItem findCartItemByCartIdAndProductId(Long cartId, Long productId);

    CartItem upDownQuantity(Long id, int quantity);
}
