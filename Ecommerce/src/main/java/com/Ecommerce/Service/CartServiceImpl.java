package com.Ecommerce.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Dao.CartRepository;
import com.Ecommerce.Dao.OrderHdRepository;
import com.Ecommerce.Dao.OrderItemRepository;
import com.Ecommerce.Entity.Cart;
import com.Ecommerce.Entity.OrderItem;
import com.Ecommerce.ServiceInterfaces.CartService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	public String saveCartItem(Cart cart)
	{
		boolean existsByCustomerIdAndItemId = cartRepository.existsByCustomerIdAndItemId(cart.getCustomerId(), cart.getItemId());
		LocalDate date = LocalDate.now();
		cart.setDate(date);
		System.err.println(date);
		
		if(existsByCustomerIdAndItemId)
		{
			
			Cart cart2 = cartRepository.findByCustomerIdAndItemId(cart.getCustomerId(), cart.getItemId());
			cart.setId(cart2.getId());
			if(cart.getQuantity()==0)
			{
				//boolean deleteByCustomerIdAndItemId = cartRepository.deleteByCustomerIdAndItemId(cart.getCustomerId(), cart.getItemId());
				cartRepository.deleteById(cart2.getId());
				System.err.println("item deleted from cart table");
				boolean existsByCustomerIdAndItemId2 = orderItemRepository.existsByCustomerIdAndItemId(cart.getCustomerId(), cart.getItemId());
				if(existsByCustomerIdAndItemId2)
				{
					
					OrderItem orderItem2 = orderItemRepository.findByCustomerIdAndItemId(cart.getCustomerId(), cart.getItemId());
					orderItemRepository.deleteById(orderItem2.getId());
					
				}
			}
			else
			cartRepository.save(cart);
		}
		else
			cartRepository.save(cart);
		System.err.println(existsByCustomerIdAndItemId);
		return "Save item to cart";
		
	}

	public List<Cart> findAllCarts()
	{
		List<Cart> list = cartRepository.findAll();
		return list;
		
	}
	// find by cut=stomer id in 
	public List<Cart> findAllByCustomerId(int customerId)
	{
		List<Cart> list = cartRepository.findAllByCustomerId(customerId);
		return list;
		
	}
	
	public List<Cart> findAllByType(String cartType)
	{
		List<Cart> list = cartRepository.findAll();
		return list;
		
	}
	
	public List<Cart> findByCustomerIdAndType(int customerId, String cartType)
	{
		
		List<Cart> list =cartRepository.findByCustomerIdAndType(customerId, cartType);
		return list;
		
	}
}
