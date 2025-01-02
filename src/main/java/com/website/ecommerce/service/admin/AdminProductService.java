package com.website.ecommerce.service.admin;

import com.website.ecommerce.dtos.adminDTOs.AdminCreateProductDTO;
import com.website.ecommerce.dtos.adminDTOs.AdminUpdateProductDTO;
import com.website.ecommerce.model.Category;
import com.website.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminProductService {

    Boolean existsProductByName(String name);

    Product createProduct(AdminCreateProductDTO adminCreateProductDto, MultipartFile img);

    Product updateProduct(AdminUpdateProductDTO adminUpdateProductDTO, MultipartFile img);

    void deleteProduct(Long id);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Page<Product> getProductPage(int page, int size);

    Product findProductByName(String name);

    List<Product> findProductByCategory(Category category);

    List<Product> findByNameContaining(String name);

    Boolean existsProductByNameDiffId(String name, Long id);
}
