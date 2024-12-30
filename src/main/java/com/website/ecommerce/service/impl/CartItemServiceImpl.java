package com.website.ecommerce.service.impl;

import com.website.ecommerce.model.Cart;
import com.website.ecommerce.model.CartItem;
import com.website.ecommerce.repository.CartItemRepository;
import com.website.ecommerce.service.CartItemService;
import com.website.ecommerce.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private CartItemRepository cartItemRepository;
    private CartService cartService;

    @Override
    public CartItem addCartItem(CartItem cartItem) {
        int quantity = quantityByCartIdAndProductId(cartItem.getCart().getId(), cartItem.getProduct().getId());
        if(quantity == 0){
            CartItem addCartItem = cartItemRepository.save(cartItem);
            cartService.updateTotalCart(cartItem.getCart().getId());
            return addCartItem;
        }else{
            CartItem updateCartItem = updateCartItemQuantityBuy(cartItem);
            cartService.updateTotalCart(cartItem.getCart().getId());
            return updateCartItem;
        }
    }

    @Override
    public CartItem updateCartItem(CartItem cartItem) {
        CartItem existCartItem = findCartItemById(cartItem.getId());
        existCartItem.setQuantityBuy(cartItem.getQuantityBuy());
        CartItem updateCartItem = cartItemRepository.save(existCartItem);
        return updateCartItem;
    }

    @Override
    public CartItem updateCartItemQuantityBuy(CartItem cartItem){
        CartItem ExistCartItem = findCartItemByCartIdAndProductId(cartItem.getCart().getId(), cartItem.getProduct().getId());
        ExistCartItem.setQuantityBuy(cartItem.getQuantityBuy() + ExistCartItem.getQuantityBuy());
        CartItem updateCartItem = cartItemRepository.save(ExistCartItem);
        return updateCartItem;
    }

    @Override
    public void deleteCartItem(int id) {
        CartItem cartItem = findCartItemById(id);
        cartItemRepository.deleteById(id);
        cartService.updateTotalCart(cartItem.getCart().getId());
    }

    @Override
    public CartItem findCartItemById(int id){
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        return cartItem.get();
    }

    @Override
    public CartItem findCartItemByCartIdAndProductId(int cartId, int productId){
        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cartId, productId);
        return cartItem;
    }

    @Override
    public CartItem upDownQuantity(int id, int quantity) {
        CartItem existCartItem = findCartItemById(id);
        existCartItem.setQuantityBuy(existCartItem.getQuantityBuy() + quantity);
        CartItem updateCartItem = cartItemRepository.save(existCartItem);
        cartService.updateTotalCart(updateCartItem.getCart().getId());
        return updateCartItem;
    }

    @Override
    public int quantityByCartIdAndProductId(int cartId, int productId) {
        Integer quantity = cartItemRepository.quantityByCartIdAndProductId(cartId, productId);
        return (quantity != null) ? quantity : 0;
    }

    @Override
    public List<CartItem> getCartItemsByCartId(int cartId) {
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        return cartItems;
    }
}
