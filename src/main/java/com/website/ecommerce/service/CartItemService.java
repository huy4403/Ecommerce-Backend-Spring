package com.website.ecommerce.service;

import com.website.ecommerce.model.Cart;
import com.website.ecommerce.model.CartItem;

import java.util.List;

public interface CartItemService {

    CartItem addCartItem(CartItem cartItem);

    CartItem updateCartItem(CartItem cartItem);

    void deleteCartItem(int id);

    List<CartItem> getCartItemsByCartId(int cartId);

    CartItem findCartItemById(int id);

    int quantityByCartIdAndProductId(int cartId, int productId);

    CartItem updateCartItemQuantityBuy(CartItem cartItem);

    CartItem findCartItemByCartIdAndProductId(int cartId, int productId);

    CartItem upDownQuantity(int id, int quantity);
}
