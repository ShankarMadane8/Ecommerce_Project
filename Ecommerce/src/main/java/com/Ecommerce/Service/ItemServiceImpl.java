package com.Ecommerce.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.Ecommerce.Dao.ItemRepository;

import com.Ecommerce.Entity.Item;
import com.Ecommerce.ServiceInterfaces.ItemService;

@Service 
public class ItemServiceImpl  implements ItemService{

	@Autowired
	ItemRepository itemRepository;
	
	
	
	
	public String saveItem(Item item)
	{
		item.setHtml("emptyhtml");
		itemRepository.save(item);
			System.out.println(item);
		return "Congratulations "+ item.getName()+" is Added Successfully";
	}
	
	public List<Item> getAllItems()
	{
		List<Item> listItems = itemRepository.findAll();
		return listItems;
	}
	
	public List<Item> getAllItemsByCartTypes(String cartType)
	{
		List<Item> listItems = itemRepository.findAll();
		return listItems;
	}
	
	public Page<Item> getAllItems(int page)
	{
		PageRequest pageRequest = PageRequest.of(page, 4);
		Page<Item> pageItem = itemRepository.findAll(pageRequest);
		return pageItem;
	}
	public Item getItem(int id)
	{
		Item item = itemRepository.findById(id).get();
		return item;
	}
	
	public void deleteProduct(int productid) {
		itemRepository.deleteById(productid);
		
	}
}
