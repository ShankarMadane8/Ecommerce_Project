package com.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Address;



@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
   
}
