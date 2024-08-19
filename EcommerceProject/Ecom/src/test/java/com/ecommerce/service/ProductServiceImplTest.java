package com.ecommerce.service;

import com.ecommerce.dao.ProductRepository;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.exceptions.CategoryNotFoundException;
import com.ecommerce.service.CategoryService;
import com.ecommerce.serviceImpl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDTO productDTO;
    private Category category;

    @BeforeEach
    void setUp() {
        // Initialize test data
        category = new Category();
        category.setId(1);
        category.setName("Electronics");

        product = new Product();
        product.setId(1);
        product.setName("Laptop");
        product.setBrand("Brand");
        product.setCategory(category);  // Ensure category is set
        product.setImage(new byte[]{1, 2, 3});
        product.setDescription("A high-performance laptop.");
        product.setPrice(1200.00f);
        product.setDiscount(10.0f);

        productDTO = new ProductDTO(product);
    }

    @Test
    void createProduct_ShouldReturnProduct() {
        when(categoryService.getCategoryById(anyInt())).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(1, product);

        assertNotNull(createdProduct);
        assertEquals("Laptop", createdProduct.getName());
        assertEquals(category, createdProduct.getCategory());

        verify(categoryService, times(1)).getCategoryById(anyInt());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void createProduct_ShouldThrowException_WhenCategoryNotFound() {
        when(categoryService.getCategoryById(anyInt())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> productService.createProduct(1, product));

        verify(categoryService, times(1)).getCategoryById(anyInt());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    void updateProduct_ShouldReturnUpdatedProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product updatedProduct = productService.updateProduct(1, product);

        assertNotNull(updatedProduct);
        assertEquals("Laptop", updatedProduct.getName());

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void deleteProduct_ShouldCallDeleteById() {
        doNothing().when(productRepository).deleteById(anyInt());

        productService.deleteProduct(1);

        verify(productRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void getProductById_ShouldReturnProductDTO() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        Optional<ProductDTO> result = productService.getProductById(1);

        assertTrue(result.isPresent());
        assertEquals("Laptop", result.get().getName());

        verify(productRepository, times(1)).findById(anyInt());
    }

    @Test
    void getProductById_ShouldReturnEmpty_WhenProductNotFound() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

        Optional<ProductDTO> result = productService.getProductById(1);

        assertFalse(result.isPresent());

        verify(productRepository, times(1)).findById(anyInt());
    }

    @Test
    void getAllProducts_ShouldReturnListOfProductDTOs() {
        List<Product> products = new ArrayList<>();
        products.add(product);

        when(productRepository.findAll()).thenReturn(products);

        List<ProductDTO> productDTOs = productService.getAllProducts();

        assertNotNull(productDTOs);
        assertEquals(1, productDTOs.size());
        assertEquals("Laptop", productDTOs.get(0).getName());

        verify(productRepository, times(1)).findAll();
    }
}
