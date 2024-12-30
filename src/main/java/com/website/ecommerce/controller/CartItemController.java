package com.website.ecommerce.controller;

import com.website.ecommerce.model.CartItem;
import com.website.ecommerce.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("api/cartItem")
public class CartItemController {
    private CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<?> addCartItem(@RequestBody CartItem cartItem) {
        CartItem cartItemResult = cartItemService.addCartItem(cartItem);
        if(cartItemResult == null) {
            return new ResponseEntity<>("Không thể thêm vào giỏ hàng",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartItemResult, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem) {
        CartItem cartItemResult = cartItemService.updateCartItem(cartItem);
        return new ResponseEntity<>(cartItemResult, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable("id") int id) {
        cartItemService.deleteCartItem(id);
        return new ResponseEntity<>("Xóa thành công sản phẩm khỏi giỏ hàng",HttpStatus.OK);
    }
    @GetMapping("get-all/{cartId}")
    public ResponseEntity<?> getCartItems(@PathVariable("cartId") int cartId) {
        List<CartItem> cartItems = cartItemService.getCartItemsByCartId(cartId);
        if(cartItems.isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Giỏ hàng đang trống");
            return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> quantityByCartIdAndProductId(@RequestParam("cartId") int cartId, @RequestParam("productId") int productId) {
        int quantity = cartItemService.quantityByCartIdAndProductId(cartId, productId);
        return new ResponseEntity<>(quantity, HttpStatus.OK);
    }

    @PutMapping("updownQuantity/{id}")
    public ResponseEntity<?> upDownQuantity(@PathVariable("id") int id, @RequestBody int quantity) {
        CartItem cartItem = cartItemService.upDownQuantity(id, quantity);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }
}
