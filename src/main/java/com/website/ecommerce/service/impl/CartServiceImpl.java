package com.website.ecommerce.service.impl;

import com.website.ecommerce.model.Cart;
import com.website.ecommerce.repository.CartRepository;
import com.website.ecommerce.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Cart cart) {
        Cart existCart = cartRepository.findByUserId(cart.getUser().getId());
        existCart.setTotal(cart.getTotal());
        Cart updateCart = cartRepository.save(existCart);
        return updateCart;
    }

    @Override
    public void updateTotalCart(int cartId){
        int total = cartRepository.calculateTotal(cartId);
        cartRepository.updateCartTotal(cartId, total);
    }

    @Override
    public Cart getCartByUserId(int id) {
        Cart cart = cartRepository.findByUserId(id);
        return cart;
    }
}