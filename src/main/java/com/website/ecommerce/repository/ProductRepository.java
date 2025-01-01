package com.website.ecommerce.repository;

import com.website.ecommerce.model.Category;
import com.website.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Boolean existsProductByName(String name);

    Product findProductByName(String name);

    List<Product> findProductByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findByNameContaining(@Param("name") String name);

    @Query("SELECT COUNT(p) > 0 FROM Product p WHERE p.name = :name AND p.id != :id")
    Boolean existsProductByNameDiffId(String name, Long id);

    @Query("SELECT COUNT(p) FROM Product p")
    Long countAllProducts();
}
