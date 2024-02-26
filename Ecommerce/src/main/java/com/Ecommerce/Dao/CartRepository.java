package com.Ecommerce.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.Entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	public boolean existsByCustomerIdAndItemId(int customerId, int itemId);
	
	public Cart findByCustomerIdAndItemId(int customerId, int itemId);
	
	public List<Cart> findByCustomerIdAndType(int customerId, String cartType);
	
	public Integer deleteByCustomerIdAndItemId(int customerId, int itemId);
	
	public List<Cart> findAllByCustomerId(int customerId);
	
	
	public Integer countByCustomerIdAndItemId(int customerId, int itemId);
}
