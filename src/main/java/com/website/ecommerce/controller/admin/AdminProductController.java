package com.website.ecommerce.controller.admin;

import com.website.ecommerce.dtos.adminDTOs.AdminCreateProductDTO;
import com.website.ecommerce.dtos.adminDTOs.AdminUpdateProductDTO;
import com.website.ecommerce.model.Product;
import com.website.ecommerce.service.Upload.StorageService;
import com.website.ecommerce.service.admin.AdminProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("api/admin/products")
public class AdminProductController {
    @Autowired
    private AdminProductService adminProductService;

    @PostMapping("create")
    public ResponseEntity<?> createProduct(@Valid @ModelAttribute AdminCreateProductDTO productDto,
                                           @RequestParam("img") MultipartFile img) {
        Product createdProduct = adminProductService.createProduct(productDto, img);
        return ResponseEntity.status(HttpStatus.OK).body(createdProduct);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @ModelAttribute AdminUpdateProductDTO adminUpdateProductDTO, @RequestParam(value = "file", required = false) MultipartFile file) {
        adminUpdateProductDTO.setId(id);
        Product updateProduct = adminProductService.updateProduct(adminUpdateProductDTO, file);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @GetMapping("{id}/show")
    public ResponseEntity<?> showProduct(@PathVariable("id") Long id) {
        Product product = adminProductService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        adminProductService.deleteProduct(id);
        return new ResponseEntity<>("Xóa thành công sản phẩm", HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = adminProductService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProductsByName(@RequestParam("name") String name) {
        List<Product> products = adminProductService.findByNameContaining(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
