package com.website.ecommerce.controller.publics;

import com.website.ecommerce.model.Category;
import com.website.ecommerce.model.Product;
import com.website.ecommerce.service.publics.PublicCategoryService;
import com.website.ecommerce.service.publics.PublicProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("api/public")
public class PublicController {
    @Autowired
    private PublicProductService publicProductService;

    @Autowired
    private PublicCategoryService publicCategoryService;

    @GetMapping("calculator-pagination")
    public ResponseEntity<?> countAllProducts(){
        Long count = publicProductService.countAllProducts();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("filter-product-by-category")
    public ResponseEntity<?> findProductByCategory(@RequestBody Category category) {
        List<Product> products = publicProductService.findProductByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("pagination")
    public ResponseEntity<?> getProductPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9") int size) {
        Page<Product> products = publicProductService.getProductPage(page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("categories/get-all")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = publicCategoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("product/{id}/show")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        Product product = publicProductService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchProductsByName(@RequestParam("name") String name) {
        List<Product> products = publicProductService.findByNameContaining(name);
        if(products == null){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Không tìm thấy sản phẩm " +name);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
