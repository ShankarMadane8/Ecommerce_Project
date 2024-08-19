package com.ecommerce.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entity.Category;
import com.ecommerce.exceptions.CategoryNotFoundException;
import com.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.ProductRepository;

import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	   @Autowired
	   private ProductRepository productRepository;

	   @Autowired
	   private CategoryService categoryService;



	   @Override
	    public Product createProduct(int categoryId, Product product)  {
		   Optional<Category> optionalCategory = categoryService.getCategoryById(categoryId);

		   Category category = optionalCategory.orElseThrow(() ->
				    new CategoryNotFoundException("Category not found with ID: " + categoryId));

		   product.setCategory(category);
		   return productRepository.save(product);
	    }

	    @Override
	    public Product updateProduct(int id, Product product) {
		    product.setId(id);
	        return productRepository.save(product);
	    }

	    @Override
	    public void deleteProduct(int id) {
	        productRepository.deleteById(id);
	    }

	    @Override
	    public Optional<ProductDTO> getProductById(int id) {
			Optional<Product> productOptional = productRepository.findById(id);
			return productOptional.map(ProductDTO::new);
	    }

	    @Override
	    public List<ProductDTO> getAllProducts() {
			List<Product> productList = productRepository.findAll();
			return productList.stream()
					.map(ProductDTO::new)
					.collect(Collectors.toList());
		}

}
