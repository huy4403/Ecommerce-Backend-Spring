package com.website.ecommerce.service;

import com.website.ecommerce.dtos.productDTOs.ProductCreateDTO;
import com.website.ecommerce.model.Category;
import com.website.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    Boolean existsProductByName(String name);

    Product createProduct(ProductCreateDTO productCreateDto, MultipartFile img);

    Product updateProduct(Product product);

    void deleteProduct(int id);

    Product getProductById(int id);

    List<Product> getAllProducts();

    Page<Product> getProductPage(int page, int size);

    Product findProductByName(String name);

    List<Product> findProductByCategory(Category category);

    List<Product> findByNameContaining(String name);

    Boolean existsProductByNameDiffId(String name, int id);

    int countAllProducts();
}
