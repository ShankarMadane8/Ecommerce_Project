package com.Ecommerce.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.Entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	public boolean existsByCustomerId(int customerId);
	
	public Payment findByCustomerId(int customerId);
}
