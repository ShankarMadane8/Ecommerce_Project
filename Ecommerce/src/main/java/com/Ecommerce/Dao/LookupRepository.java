package com.Ecommerce.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.Entity.Lookup;

@Repository
public interface LookupRepository extends JpaRepository<Lookup, Integer>{
	
}
