package com.website.ecommerce.service;

import com.website.ecommerce.model.Category;

import java.util.List;

public interface CategoryService {

    //Add cate
    Category createCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(int id);

    Category getCategoryById(int id);

    List<Category> getAllCategories();
}
