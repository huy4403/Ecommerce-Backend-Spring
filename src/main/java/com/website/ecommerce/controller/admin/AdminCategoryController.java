package com.website.ecommerce.controller.admin;

import com.website.ecommerce.model.Category;
import com.website.ecommerce.service.admin.AdminCategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("api/admin/categories")
public class AdminCategoryController {
    @Autowired
    private AdminCategoryService adminCategoryService;

    @PostMapping("create")
    public ResponseEntity<?> createCategory(@ModelAttribute Category category) {
        Category newCategory = adminCategoryService.save(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<?> updateCategory(@PathVariable int id, @ModelAttribute Category category) {
        category.setId(id);
        Category updateCategory = adminCategoryService.updateById(category);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteCategory(@PathVariable int id) {
        adminCategoryService.deleteById(id);
        return new ResponseEntity<>("Xóa thành công danh mục", HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = adminCategoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("{id}/show")
    public ResponseEntity<?> showCategory(@PathVariable int id) {
        Category category = adminCategoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<?> searchCategoryByName(@RequestParam String name) {
        List<Category> categories = adminCategoryService.findByNameContaining(name);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
