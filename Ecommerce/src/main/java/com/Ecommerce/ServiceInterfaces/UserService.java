package com.Ecommerce.ServiceInterfaces;

import com.Ecommerce.Entity.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {

	String saveUser(User user);
	boolean existsByEmailPassword(String email, String password);
	 boolean isExistsByEmail(String email);
	 boolean changePassword(String newpassword, String confirmpassword ,HttpSession session);
	 User getUserByEmail(String email);
}
