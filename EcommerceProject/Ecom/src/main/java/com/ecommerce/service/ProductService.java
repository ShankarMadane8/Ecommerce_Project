package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entity.Product;

public interface ProductService {
	Product createProduct(int categoryId, Product product);
    Product updateProduct(int id,Product product);
    void deleteProduct(int id);
    Optional<ProductDTO> getProductById(int id);
    List<ProductDTO> getAllProducts();
}
