package com.Ecommerce.ServiceInterfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.Ecommerce.Entity.Item;

public interface ItemService {

	String saveItem(Item item);
	List<Item> getAllItems();
	List<Item> getAllItemsByCartTypes(String cartType);
	Page<Item> getAllItems(int page);
	Item getItem(int id);
	void deleteProduct(int productid);
}
