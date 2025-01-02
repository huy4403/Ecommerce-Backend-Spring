package com.website.ecommerce.service.publics;

import com.website.ecommerce.model.Category;
import com.website.ecommerce.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PublicProductService {
    Product getProductById(Long id);
    Page<Product> getProductPage(int page, int size);
    Long countAllProducts();
    List<Product> findProductByCategory(Category category);
    List<Product> findByNameContaining(String name);
}
