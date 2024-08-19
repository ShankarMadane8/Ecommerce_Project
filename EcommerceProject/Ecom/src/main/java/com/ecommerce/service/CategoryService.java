package com.ecommerce.service;

import com.ecommerce.entity.Category;
import com.ecommerce.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CategoryService {

    Category createCategory(Category category);
    Optional<Category> getCategoryById(int id);
    List<Category> getAllCategories();
    Category updateCategory(int id, Category category);
    void deleteCategory(int id);
}
