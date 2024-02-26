package com.Ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Dao.OrderHdRepository;
import com.Ecommerce.Entity.OrderHd;
import com.Ecommerce.ServiceInterfaces.OrderHdService;

@Service
public class OrderHdServiceImpl implements OrderHdService {
	
	@Autowired
	OrderHdRepository orderHdRepository;
	
	public String saveOrderHd(OrderHd orderHd)
	{
		orderHdRepository.save(orderHd);
		System.out.println("Order Hd  Saved");
		return "Order Hd  Saved";
	}
}
