package com.Ecommerce.ServiceInterfaces;

import java.util.List;

import com.Ecommerce.Entity.Cart;
import com.Ecommerce.Entity.OrderItem;

import jakarta.servlet.http.HttpServletRequest;

public interface OrderItemService {
	String saveOrder(HttpServletRequest request, List<Cart> list);
	 List<OrderItem> findAll();

}
