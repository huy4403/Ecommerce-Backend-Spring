package com.website.ecommerce.service.publics;

import com.website.ecommerce.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PublicProductService {
    Product getProductById(Long id);
    Page<Product> getProductPage(int page, int size);
    Long countAllProducts();

    List<Product> getProductsByNameAndCategory(String name, Integer categoryId);
}
