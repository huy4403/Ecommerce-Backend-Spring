package com.website.ecommerce.service.publics.impl;

import com.website.ecommerce.exception.HandleException;
import com.website.ecommerce.model.Category;
import com.website.ecommerce.model.Product;
import com.website.ecommerce.repository.CategoryRepository;
import com.website.ecommerce.repository.ProductRepository;
import com.website.ecommerce.service.publics.PublicProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PublicProductServiceImpl implements PublicProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Không tìm thấy sản phẩm này");
            throw new HandleException(errors);
        }
        return product.get();
    }

    @Override
    public Page<Product> getProductPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }
    @Override
    public Long countAllProducts() {
        Long count = productRepository.countAllProducts();
        return count;
    }

    @Override
    public List<Product> getProductsByNameAndCategory(String name, Integer categoryId) {
        List<Product> products = productRepository.findByNameContainingAndCategory(name ,categoryId);
        if (products.isEmpty()) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Không tìm thấy sản phẩm: " + name + ".");
            throw new HandleException(errors);
        }
        return products;
    }
}
