package com.ecommerce.service;

import com.ecommerce.dao.CategoryRepository;
import com.ecommerce.entity.Category;
import com.ecommerce.exceptions.CategoryNotFoundException;
import com.ecommerce.serviceImpl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        category = new Category();
        category.setId(1);
        category.setName("Electronics");
    }

    @Test
    void createCategory_ShouldReturnCategory() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category createdCategory = categoryService.createCategory(category);

        assertNotNull(createdCategory);
        assertEquals(1, createdCategory.getId());
        assertEquals("Electronics", createdCategory.getName());

        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void getCategoryById_ShouldReturnCategory() {
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(category));

        Optional<Category> foundCategory = categoryService.getCategoryById(1);

        assertTrue(foundCategory.isPresent());
        assertEquals(1, foundCategory.get().getId());
        assertEquals("Electronics", foundCategory.get().getName());

        verify(categoryRepository, times(1)).findById(anyInt());
    }

    @Test
    void getCategoryById_ShouldReturnEmpty_WhenCategoryNotFound() {
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.empty());

        Optional<Category> foundCategory = categoryService.getCategoryById(1);

        assertFalse(foundCategory.isPresent());

        verify(categoryRepository, times(1)).findById(anyInt());
    }

    @Test
    void getAllCategories_ShouldReturnListOfCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> categoryList = categoryService.getAllCategories();

        assertNotNull(categoryList);
        assertEquals(1, categoryList.size());
        assertEquals("Electronics", categoryList.get(0).getName());

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void updateCategory_ShouldReturnUpdatedCategory() {
        when(categoryRepository.existsById(anyInt())).thenReturn(true);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category updatedCategory = categoryService.updateCategory(1, category);

        assertNotNull(updatedCategory);
        assertEquals(1, updatedCategory.getId());
        assertEquals("Electronics", updatedCategory.getName());

        verify(categoryRepository, times(1)).existsById(anyInt());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void updateCategory_ShouldThrowException_WhenCategoryNotFound() {
        when(categoryRepository.existsById(anyInt())).thenReturn(false);

        assertThrows(CategoryNotFoundException.class, () -> categoryService.updateCategory(1, category));

        verify(categoryRepository, times(1)).existsById(anyInt());
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    void deleteCategory_ShouldCallDeleteById() {
        when(categoryRepository.existsById(anyInt())).thenReturn(true);

        categoryService.deleteCategory(1);

        verify(categoryRepository, times(1)).existsById(anyInt());
        verify(categoryRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteCategory_ShouldThrowException_WhenCategoryNotFound() {
        when(categoryRepository.existsById(anyInt())).thenReturn(false);

        assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteCategory(1));

        verify(categoryRepository, times(1)).existsById(anyInt());
        verify(categoryRepository, never()).deleteById(anyInt());
    }
}
