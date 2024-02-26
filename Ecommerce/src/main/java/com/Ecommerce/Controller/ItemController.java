package com.Ecommerce.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.Ecommerce.Entity.Item;
import com.Ecommerce.Entity.ItemDao;
import com.Ecommerce.Entity.LoginResponse;
import com.Ecommerce.Entity.User;
import com.Ecommerce.Service.ItemServiceImpl;
import com.Ecommerce.Service.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;

@Controller
@CrossOrigin(origins = "http://localhost:3000/")
public class ItemController {
		@Autowired
		ItemServiceImpl itemService;
		
		 @Autowired
		 UserServiceImpl userService;
		
		
		@GetMapping("itemform")
		public String itemform()
		{
			return "itemform";
		}
		
		//----------------------- Item Form---------------------------
		@PostMapping("itemsub")
		public String itemForm(@ModelAttribute Item item,MultipartFile productImage,MultipartFile productImage2,  Model model,HttpServletRequest request) throws IOException
		{
			
			System.err.println("item sub");
			item.setImage(productImage.getBytes());
			String msg= itemService.saveItem(item);
			model.addAttribute("msg",msg);
			//System.out.println(itemDao);
			
			//@RequestParam MultipartFile itemImage1, @RequestParam MultipartFile itemImage2
			return "itemform";
		}
				
		
		
			
//		@GetMapping("home2")
//		String home2(){
//			
//		    System.err.println("Home page 2");
//		    return "home2";
//		}
		
		
		
		
		
		
		
		
		
		
		
		
		//================== Rest API ==========================
		
		
		@GetMapping("products")
		@ResponseBody
		List<Item> getallProducts(){
			List<Item> items = itemService.getAllItems();
			
			System.err.println("fetch all produts using Ajax");
			List<Item> products = new ArrayList<>();
			
			for (Item item : items) {
				byte[] image = item.getImage();
				if (image != null) {
					String imagePath = Base64.getEncoder().encodeToString(image);
					item.setImagePath(imagePath);
					
				}
				products.add(item);
			}
			return products;
		}
		
		
		
		   @DeleteMapping("/products/{productId}")
		   @ResponseBody
		    public String deleteProduct(@PathVariable int productId) {
			   
			   System.err.println("delete product id: "+productId);
			   itemService.deleteProduct(productId);
			return "delete product ";			 
		 }	
		   
		   
		   
		      
		@PostMapping("loginUser2") 
		@ResponseBody
		public ResponseEntity<LoginResponse>  loginUser(@RequestBody User user) {
			Encoder encoder = Base64.getEncoder();
			String encodePassword = encoder.encodeToString(user.getPassword().getBytes());
			user.setPassword(encodePassword);
			boolean existsByEmailPassword = userService.existsByEmailPassword(user.getEmail(), user.getPassword());
			LoginResponse loginResponse=new LoginResponse();
			loginResponse.setLogin(existsByEmailPassword);
			String msg=existsByEmailPassword?"User Login Successfully!":"please Enter Correct userName and password";
			loginResponse.setMessage(msg);
			if(existsByEmailPassword) {
				User user2 = userService.getUserByEmail(user.getEmail());
				loginResponse.setUser(user2);
			}
			return ResponseEntity.ok(loginResponse);
			
		}
		   
		   
		   
}
