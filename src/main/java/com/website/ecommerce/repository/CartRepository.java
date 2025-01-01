package com.website.ecommerce.repository;

import com.website.ecommerce.model.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);

    @Query("SELECT COALESCE(SUM(p.price * ci.quantityBuy), 0) " +
            "FROM CartItem ci " +
            "JOIN ci.product p " +
            "WHERE ci.cart.id = :cartId")
    int calculateTotal(@Param("cartId") Long cartId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE carts c SET c.total = :total WHERE c.id = :cartId", nativeQuery = true)
    void updateCartTotal(@Param("cartId") Long cartId, @Param("total") double total);
}
