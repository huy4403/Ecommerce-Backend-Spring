package com.website.ecommerce.service.impl;

import com.website.ecommerce.dtos.productDTOs.ProductCreateDTO;
import com.website.ecommerce.exception.ValidateException;
import com.website.ecommerce.model.Category;
import com.website.ecommerce.model.Product;
import com.website.ecommerce.repository.ProductRepository;
import com.website.ecommerce.service.ProductService;
import com.website.ecommerce.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private StorageService storageService;
    @Override
    public Product createProduct(ProductCreateDTO productCreateDto, MultipartFile img) {

        boolean existProduct = productRepository.existsProductByName(productCreateDto.getName());
        if (existProduct) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("name", "Sản phẩm đã tồn tại");
            throw new ValidateException(errors);
        }
        this.storageService.store(img);

        Product product = new Product();
        product.setName(productCreateDto.getName());
        product.setQuantity(productCreateDto.getQuantity());
        product.setImportPrice(productCreateDto.getImportPrice());
        product.setPrice(productCreateDto.getPrice());
        product.setImg(img.getOriginalFilename());
        product.setDescription(productCreateDto.getDescription());
        product.setCategory(productCreateDto.getCategory());

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product existProduct = getProductById(product.getId());
        if(product.getName() != null){
            existProduct.setName(product.getName());
        }
        if(product.getQuantity() != -1){
            existProduct.setQuantity(product.getQuantity());
        }
        if(product.getImportPrice() != -1){
            existProduct.setImportPrice(product.getImportPrice());
        }
        if(product.getPrice() != -1){
            existProduct.setPrice(product.getPrice());
        }
        if(product.getImg() != null && !(product.getImg().isEmpty())){
            existProduct.setImg(product.getImg());
        }
        if(product.getDescription() != null){
            existProduct.setDescription(product.getDescription());
        }
        if(product.getCategory() != null){
            existProduct.setCategory(product.getCategory());
        }
        Product updateProduct = productRepository.save(existProduct);
        return updateProduct;
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }

    @Override
    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Page<Product> getProductPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    @Override
    public Boolean existsProductByName(String name){
        return productRepository.existsProductByName(name);
    }

    @Override
    public Product findProductByName(String name){
        Product product = productRepository.findProductByName(name);
        return product;
    }

    @Override
    public List<Product> findProductByCategory(Category category){
        List<Product> products = productRepository.findProductByCategory(category);
        return products;
    }

    @Override
    public List<Product> findByNameContaining(String name){
        List<Product> products = productRepository.findByNameContaining(name);
        return products;
    }

    @Override
    public Boolean existsProductByNameDiffId(String name, Long id) {
        return productRepository.existsProductByNameDiffId(name, id);
    }

    @Override
    public Long countAllProducts() {
        Long count = productRepository.countAllProducts();
        return count;
    }
}
