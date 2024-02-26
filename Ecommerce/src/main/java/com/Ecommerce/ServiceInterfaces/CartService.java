package com.Ecommerce.ServiceInterfaces;

import java.util.List;

import com.Ecommerce.Entity.Cart;

public interface CartService {
	
	String saveCartItem(Cart cart);
	List<Cart> findAllCarts();
	List<Cart> findAllByCustomerId(int customerId);
	List<Cart> findAllByType(String cartType);
	List<Cart> findByCustomerIdAndType(int customerId, String cartType);

}
