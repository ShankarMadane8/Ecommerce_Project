package com.Ecommerce.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Ecommerce.Entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	    @Query("SELECT a FROM Address a WHERE a.user_id = :userId")
	    List<Address> findByUserId(@Param("userId") int userId);

}
