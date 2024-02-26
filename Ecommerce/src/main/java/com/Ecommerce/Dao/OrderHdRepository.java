package com.Ecommerce.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.Entity.OrderHd;

@Repository
public interface OrderHdRepository extends JpaRepository<OrderHd, Integer>{

	public boolean existsByCustomerId(int customerId);
	
	public OrderHd findByCustomerId(int customerId);
}
