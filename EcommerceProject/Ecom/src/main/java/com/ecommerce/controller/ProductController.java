package com.ecommerce.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.ecommerce.constant.MessageConstant;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entity.Category;
import com.ecommerce.exceptions.CategoryNotFoundException;
import com.ecommerce.response.ResponseHandler;
import com.ecommerce.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import com.ecommerce.serviceImpl.ProductServiceImpl;

@RestController
@RequestMapping("/products")
@CrossOrigin
@Tag(name = "Product", description = "Operations related to product management")
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productService;


    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Create a new product", description = "Create a new product with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
	@PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestParam("name") String name,
            @RequestParam("brand") String brand,
            @RequestParam("category") int categoryId,
            @RequestParam("image") MultipartFile image,
            @RequestParam("description") String description,
            @RequestParam("price") float price,
            @RequestParam("discount") float discount) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setBrand(brand);
        product.setImage(image.getBytes());
        product.setDescription(description);
        product.setPrice(price);
        product.setDiscount(discount);

        Product savedProduct = productService.createProduct(categoryId, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @Operation(summary = "Update an existing product", description = "Update an existing product identified by the product ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product ID or input"),
            @ApiResponse(responseCode = "404", description = "Category not found or product not found")
    })
    @PutMapping("update/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable int id,
            @RequestParam("name") String name,
            @RequestParam("brand") String brand,
            @RequestParam("category") int categoryId,
            @RequestParam("image") MultipartFile image,
            @RequestParam("description") String description,
            @RequestParam("price") float price,
            @RequestParam("discount") float discount) throws IOException {

        Optional<Category> optionalCategory = categoryService.getCategoryById(categoryId);

        Category category = optionalCategory.orElseThrow(() ->
                new CategoryNotFoundException("Category not found with ID: " + categoryId));

        Product product = new Product();
        product.setName(name);
        product.setBrand(brand);
        product.setCategory(category);
        product.setImage(image.getBytes());
        product.setDescription(description);
        product.setPrice(price);
        product.setDiscount(discount);
        return ResponseEntity.ok(productService.updateProduct(id,product));
    }


    @Operation(summary = "Delete a product", description = "Delete an existing product identified by the product ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get product by ID", description = "Retrieve product details by product ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable int id) {
        Optional<ProductDTO> productDtoOptional = productService.getProductById(id);

        return productDtoOptional.map(product ->
                ResponseHandler.generateResponse(HttpStatus.OK, MessageConstant.MSG_SUCCESS, product)
        ).orElseGet(() ->
                ResponseHandler.generateResponse(HttpStatus.NOT_FOUND, MessageConstant.MSG_PRODUCT_NOT_FOUND, null)
        );
    }

    @Operation(summary = "Get all products", description = "Retrieve a list of all products.")
    @ApiResponse(responseCode = "200", description = "List of products retrieved successfully")
    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

}
