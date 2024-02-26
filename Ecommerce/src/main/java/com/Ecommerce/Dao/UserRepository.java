package com.Ecommerce.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	public boolean existsByEmailAndPassword(String email, String password);
	
	public boolean existsByEmail(String email);
	
	public User findByEmail(String email);
}
