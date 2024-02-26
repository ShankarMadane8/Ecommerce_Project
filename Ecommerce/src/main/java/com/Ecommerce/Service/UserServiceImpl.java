package com.Ecommerce.Service;

import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Controller.EmailSender;
import com.Ecommerce.Dao.UserRepository;
import com.Ecommerce.Entity.User;
import com.Ecommerce.ServiceInterfaces.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{
		@Autowired
		UserRepository userRepository;
		
		public String saveUser(User user)
		{
			boolean existsByEmail = userRepository.existsByEmail(user.getEmail());
			if(existsByEmail)
			{
				return "Your account is already exist ";
			}
			
			userRepository.save(user);
			EmailSender.sendEmail(user.getEmail(), "Congratulations "+user.getName()+" your Account is created successfully at Sopify");
			return "Congratulations "+user.getName()+" your Account is created successfully";
		}
		
		public boolean existsByEmailPassword(String email, String password)
		{
			boolean existsByEmailAndPassword = userRepository.existsByEmailAndPassword(email, password);
			return existsByEmailAndPassword;
			
		}
		
		public boolean isExistsByEmail(String email)
		{
			boolean existsByEmail = userRepository.existsByEmail(email);
			return existsByEmail;
		}

		public boolean changePassword(String newpassword, String confirmpassword ,HttpSession session)
		{
			if(newpassword.equals(confirmpassword))
			{
				
				User user = userRepository.findByEmail((String) session.getAttribute("email"));
				Encoder encoder = Base64.getEncoder();
				String encodePassword = encoder.encodeToString(newpassword.getBytes());
				user.setPassword(encodePassword);
				userRepository.save(user);
				System.out.println("Changed Password Succesfully ");
				return true;
			}
			else
			return false;
		}
		// to get single user
		public User getUserByEmail(String email)
		{
			User user = userRepository.findByEmail(email);
			return user;
			
		}
}
