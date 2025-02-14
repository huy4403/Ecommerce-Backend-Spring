package com.website.ecommerce.service.admin.impl;

import com.website.ecommerce.dtos.adminDTOs.AdminCreateProductDTO;
import com.website.ecommerce.dtos.adminDTOs.AdminUpdateProductDTO;
import com.website.ecommerce.exception.HandleException;
import com.website.ecommerce.model.Category;
import com.website.ecommerce.model.Product;
import com.website.ecommerce.repository.ProductRepository;
import com.website.ecommerce.service.admin.AdminProductService;
import com.website.ecommerce.service.Upload.StorageService;
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
public class AdminProductServiceImpl implements AdminProductService {
    private ProductRepository productRepository;
    private StorageService storageService;
    @Override
    public Product createProduct(AdminCreateProductDTO adminCreateProductDto, MultipartFile img) {

        boolean existProduct = productRepository.existsProductByName(adminCreateProductDto.getName());
        if (existProduct) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("name", "Sản phẩm đã tồn tại");
            throw new HandleException(errors);
        }
        this.storageService.store(img);

        Product product = new Product();
        product.setName(adminCreateProductDto.getName());
        product.setQuantity(adminCreateProductDto.getQuantity());
        product.setImportPrice(adminCreateProductDto.getImportPrice());
        product.setPrice(adminCreateProductDto.getPrice());
        product.setImg(img.getOriginalFilename());
        product.setDescription(adminCreateProductDto.getDescription());
        product.setCategory(adminCreateProductDto.getCategory());

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(AdminUpdateProductDTO adminUpdateProductDTO, MultipartFile img) {
        Product existProduct = getProductById(adminUpdateProductDTO.getId());
        if(img != null && !img.isEmpty()){
            this.storageService.store(img);
            existProduct.setImg(img.getOriginalFilename());
        }
        boolean existProductByNameDiffId = existsProductByNameDiffId(adminUpdateProductDTO.getName(), adminUpdateProductDTO.getId());
        if (existProductByNameDiffId) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", adminUpdateProductDTO.getName() + " bị trùng tên với sản phẩm khác rồi");
            throw new HandleException(errors);
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
    public Boolean existsProductByNameDiffId(String name, Long id) {
        return productRepository.existsProductByNameDiffId(name, id);
    }
}
