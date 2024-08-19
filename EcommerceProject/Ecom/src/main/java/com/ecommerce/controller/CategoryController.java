package com.ecommerce.controller;

import com.ecommerce.constant.MessageConstant;
import com.ecommerce.entity.Category;
import com.ecommerce.response.ResponseHandler;
import com.ecommerce.serviceImpl.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@Tag(name = "Category", description = "Operations related to category management")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Operation(summary = "Create a new category", description = "Create a new category with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.createCategory(category);
        return ResponseHandler.generateResponse(HttpStatus.CREATED, MessageConstant.MSG_CATEGORY_CREATED, createdCategory);
    }

    @Operation(summary = "Get category by ID", description = "Retrieve category details by category ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryById(@PathVariable int id) {

        Optional<Category> optionalCategory = categoryService.getCategoryById(id);
        return optionalCategory.map(product ->
                ResponseHandler.generateResponse(HttpStatus.CREATED, MessageConstant.MSG_SUCCESS, product)
        ).orElseGet(() ->
                ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, MessageConstant.MSG_CATEGORY_NOT_FOUND, null)
        );
    }

    @Operation(summary = "Get all categories", description = "Retrieve a list of all categories.")
    @ApiResponse(responseCode = "200", description = "List of categories retrieved successfully")
    @GetMapping
    public ResponseEntity<Object> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseHandler.generateResponse(HttpStatus.OK, MessageConstant.MSG_SUCCESS, categories);
    }

    @Operation(summary = "Update an existing category", description = "Update an existing category identified by the category ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable int id, @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return ResponseHandler.generateResponse(HttpStatus.OK, MessageConstant.MSG_CATEGORY_UPDATED, updatedCategory);
    }

    @Operation(summary = "Delete a category", description = "Delete an existing category identified by the category ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseHandler.generateResponse(HttpStatus.NO_CONTENT, MessageConstant.MSG_CATEGORY_DELETED, null);

    }

}
