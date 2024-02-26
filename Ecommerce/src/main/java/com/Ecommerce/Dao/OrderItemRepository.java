package com.Ecommerce.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.Entity.Cart;
import com.Ecommerce.Entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{
	
	public boolean existsByCustomerIdAndItemId(int customerId, int itemId); 
	
	public OrderItem findByCustomerIdAndItemId(int customerId, int itemId);
	
		

}
