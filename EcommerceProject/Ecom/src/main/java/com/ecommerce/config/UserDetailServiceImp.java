package com.ecommerce.config;

import com.ecommerce.dao.UserRepository;
import com.ecommerce.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImp implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email);
		if(user==null) {
			System.err.println("User Not Found in UserDetailServiceImpl.class .......... Not Login");
			throw new UsernameNotFoundException("No User Found !!");
		}
		CustomeUserDetails customeUserDetails=new CustomeUserDetails(user);
		return customeUserDetails;
	}

}
