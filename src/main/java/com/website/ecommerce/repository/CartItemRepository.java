package com.website.ecommerce.repository;

import com.website.ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long cartId);

    @Query("SELECT c.quantityBuy FROM CartItem c WHERE c.cart.id = :cartId AND c.product.id = :productId")
    Integer quantityByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);

    CartItem findByCartIdAndProductId(Long cartId, Long productId);
}
