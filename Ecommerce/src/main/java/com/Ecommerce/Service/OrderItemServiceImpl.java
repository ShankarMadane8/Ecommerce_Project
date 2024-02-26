package com.Ecommerce.Service;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Dao.OrderHdRepository;
import com.Ecommerce.Dao.OrderItemRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.Ecommerce.Entity.*;
import com.Ecommerce.ServiceInterfaces.OrderItemService;
@Service
public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	OrderHdRepository orderHdRepository;
	
	
	
	public String saveOrder(HttpServletRequest request, List<Cart> list)
	{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		OrderHd orderHd= new OrderHd();
		LocalDate date = LocalDate.now();
		orderHd.setDate(date);
		
		orderHd.setCustomerId(user.getId());
		int totalAmount = (int) session.getAttribute("totalAmount");
		orderHd.setAmount(totalAmount);
		orderHd.setShippingCharges(shippingCharges());
		orderHd.setTax(tax());
		orderHd.setStatus(1);
		orderHd.setStatusDate(date);
		boolean existsByCustomerId = orderHdRepository.existsByCustomerId(orderHd.getCustomerId());
		int orderHdId=0;
		if(existsByCustomerId)
		{
			OrderHd orderHd2 = orderHdRepository.findByCustomerId(orderHd.getCustomerId());
			orderHd.setId(orderHd2.getId());
			OrderHd saveOrderHd = orderHdRepository.save(orderHd);
			orderHdId=saveOrderHd.getId();
		} else {
			OrderHd saveOrderHd = orderHdRepository.save(orderHd);
			orderHdId=saveOrderHd.getId();
		}
		
		
		for(Cart cart : list)
		{
			
			if(cart.getCustomerId()==user.getId())
			{
				OrderItem orderItem= new OrderItem();
				orderItem.setPrice(cart.getPrice());
				orderItem.setCustomerId(user.getId());;
				orderItem.setDate(cart.getDate());
				orderItem.setItemId(cart.getItemId());
				orderItem.setQuantity(cart.getQuantity());
				orderItem.setShippingCharges(58);
				orderItem.setOrderhdId(orderHdId);
				orderItem.setStatus(1);
				orderItem.setAmount(cart.getPrice()*cart.getQuantity());
				
				orderItem.setCartType(cart.getType());
				totalAmount+= orderItem.getAmount();
				boolean existsByCustomerIdAndItemId = orderItemRepository.existsByCustomerIdAndItemId(orderItem.getCustomerId(), orderItem.getItemId());
				if(existsByCustomerIdAndItemId)
				{
					
					OrderItem orderItem2 = orderItemRepository.findByCustomerIdAndItemId(orderItem.getCustomerId(), orderItem.getItemId());
					orderItem.setId(orderItem2.getId());
					
					
					orderItemRepository.save(orderItem);
				}
				else
					orderItemRepository.save(orderItem);
				//orderItemRepository.save(orderItem);
				
			}
		
		}
		
		
		
//		System.err.println("All items saved ");
		return "Item Saved";
		
	}

	public List<OrderItem> findAll()
	{
		List<OrderItem> list = orderItemRepository.findAll();
		return list;
	
	}

	private double tax() {
		// TODO Auto-generated method stub
		return 102;
	}



	public double shippingCharges() {
		// TODO Auto-generated method stub
		return 101;
	}
}
