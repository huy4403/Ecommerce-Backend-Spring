package com.website.ecommerce.service.admin.impl;

import com.website.ecommerce.exception.HandleException;
import com.website.ecommerce.model.Category;
import com.website.ecommerce.repository.CategoryRepository;
import com.website.ecommerce.service.admin.AdminCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        Boolean exists = categoryRepository.existsByName(category.getName());
        if (exists) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Danh mục đã tồn tại");
            throw new HandleException(errors);
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateById(Category category) {
        Category categoryExist = getCategoryById(category.getId());
        if(categoryRepository.existsByNameDiffId(category.getName(), category.getId())) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Danh mục đã tồn tại");
            throw new HandleException(errors);
        }
        categoryExist.setName(category.getName());
        categoryExist.setIcon(category.getIcon());

        return categoryRepository.save(categoryExist);
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(!categories.isEmpty()) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Không tồn tại danh mục");
            throw new HandleException(errors);
        }
        return categories;
    }

    @Override
    public List<Category> findByNameContaining(String name) {
        List<Category> categories = categoryRepository.findByNameContaining(name);
        if(!categories.isEmpty()) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Không tìm thấy danh mục");
            throw new HandleException(errors);
        }
        return categories;
    }
    @Override
    public Category getCategoryById(int id){
        Optional<Category> category = categoryRepository.findById(id);
        if(!category.isPresent()){
            HashMap<String, String> errors = new HashMap<>();
            errors.put("message", "Danh mục không tồn tại");
            throw new HandleException(errors);
        }
        return category.get();
    }

}
