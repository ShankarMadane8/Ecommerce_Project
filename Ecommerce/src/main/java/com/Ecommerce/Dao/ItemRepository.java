package com.Ecommerce.Dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.Entity.Item;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Integer>,JpaRepository<Item, Integer>{
	//List<Item> findAll();
	
	
}
