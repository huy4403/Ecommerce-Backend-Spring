package com.website.ecommerce.service.admin;

import com.website.ecommerce.model.Category;

import java.util.List;

public interface AdminCategoryService {

    //Add cate
    Category save(Category category);

    Category updateById(Category category);

    void deleteById(int id);

    Category getCategoryById(int id);

    List<Category> getAllCategories();

    List<Category> findByNameContaining(String name);
}
