package com.website.ecommerce.controller;

import com.website.ecommerce.dtos.productDTOs.ProductCreateDTO;
import com.website.ecommerce.model.Category;
import com.website.ecommerce.model.Product;
import com.website.ecommerce.service.ProductService;
import com.website.ecommerce.service.StorageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private StorageService storageService;

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @ModelAttribute ProductCreateDTO productDto,
                                           @RequestParam("img") MultipartFile img) {
        Product createdProduct = productService.createProduct(productDto, img);
        return ResponseEntity.status(HttpStatus.OK).body(createdProduct);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") int id, @ModelAttribute Product product, @RequestParam(value = "file", required = false) MultipartFile file) {
        boolean existProductByNameDiffId = productService.existsProductByNameDiffId(product.getName(), id);
        if (existProductByNameDiffId) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", product.getName() + " bị trùng tên với sản phẩm khác rồi");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        if(file != null && !file.isEmpty()){
            this.storageService.store(file);
            product.setImg(file.getOriginalFilename());
        }
        product.setId(id);
        Product updateProduct = productService.updateProduct(product);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Xóa thành công sản phẩm", HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity<?> getProductPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9") int size) {
        Page<Product> products = productService.getProductPage(page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<?> findProductByName(@PathVariable("name") String name) {
        boolean existProduct = productService.existsProductByName(name);
        if (existProduct) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Không tìm thấy sản phẩm tên " + name);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Product product = productService.findProductByName(name);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("category")
    public ResponseEntity<?> findProductByCategory(@RequestBody Category category) {
        List<Product> products = productService.findProductByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProductsByName(@RequestParam("name") String name) {
        List<Product> products = productService.findByNameContaining(name);
        if(products == null){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Không tìm thấy sản phẩm " +name);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> countAllProducts(){
        int count = productService.countAllProducts();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
