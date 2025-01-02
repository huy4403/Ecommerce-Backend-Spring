package com.website.ecommerce.service.publics.impl;

import com.website.ecommerce.model.Category;
import com.website.ecommerce.repository.CategoryRepository;
import com.website.ecommerce.service.publics.PublicCategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PublicCategoryServiceImpl implements PublicCategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
