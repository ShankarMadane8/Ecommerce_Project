package com.Ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Dao.PaymentRepository;
import com.Ecommerce.Entity.Payment;

@Service
public class PaymentServiceImpl {
	
	@Autowired
	PaymentRepository paymentRepository;
	
	public String savepayment(Payment payment)
	{
		boolean existsByCustomerId = paymentRepository.existsByCustomerId(payment.getCustomerId());
		if(existsByCustomerId)
		{
			Payment payment2 = paymentRepository.findByCustomerId(payment.getCustomerId());
			payment.setId(payment2.getId());
			paymentRepository.save(payment);
		}
		else
		paymentRepository.save(payment);
		return "savepayment";

	}
	
}
