package com.Ecommerce.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Ecommerce.Dao.ItemRepository;
import com.Ecommerce.Entity.Item;
import com.Ecommerce.Entity.Lookup;
import com.Ecommerce.Entity.User;
import com.Ecommerce.Service.ItemServiceImpl;
import com.Ecommerce.Service.LookupServiceimpl;
import com.Ecommerce.Service.UserServiceImpl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserServiceImpl userService;

	@Autowired
	ItemServiceImpl itemService;
	
	@Autowired
	LookupServiceimpl lookupService;

	
	
	
	// -------------------------signup Get Mapping----------------------
	@GetMapping("signup")
	public String signupUser(Model model) {
		model.addAttribute("msg", null);
		return "signup";
	}

	// -------------------------signup Post Mapping----------------------
	@PostMapping("signupUser")
	public String signupUser(@ModelAttribute User user, Model model) {
		Encoder encoder = Base64.getEncoder();
		String encodePassword = encoder.encodeToString(user.getPassword().getBytes());
		user.setPassword(encodePassword);
		user.setUserType("CUSTOMER");
		String msg = userService.saveUser(user);
		model.addAttribute("msg", msg);
		System.out.println(msg);
		return "signup";
	}

	// -------------------------Login Get Mapping----------------------
	@GetMapping("login")
	public String login(HttpServletRequest request, Model model, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return "redirect:/home";
		}
		model.addAttribute("emailError",request.getParameter("emailError"));
		return "login";
	}

	// -------------------------Login Post Mapping----------------------
	@PostMapping("loginUser")
	public String loginUser(@ModelAttribute User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		Encoder encoder = Base64.getEncoder();
		String encodePassword = encoder.encodeToString(user.getPassword().getBytes());
		boolean checkEmailPass = userService.existsByEmailPassword(user.getEmail(), encodePassword);
		if (checkEmailPass) {
			
			HttpSession session = request.getSession();
			Cookie cookie= new Cookie("JSESSIONID", session.getId());
			cookie.setMaxAge(60*60*24*10);
			response.addCookie(cookie);
			user  = userService.getUserByEmail(user.getEmail());
			session.setAttribute("user", user);
			System.err.println("======================================");
			System.err.println(user);
			System.err.println("=======================================");
			return "redirect:/home";
		} else {
			model.addAttribute("error", "Username or Password is not valid please Enter valid ");
			return "login";
		}

	}
	
	//----------------------- Home Page---------------------------
	@GetMapping("home")
	public String home(HttpServletRequest request, Model model, HttpServletResponse response) {
		String alert = request.getParameter("alert");
		model.addAttribute("alert", alert);
		System.out.println(alert);
		//------------------------lookup---------------------
		List<Lookup> lookups = lookupService.getLookups();
		model.addAttribute("lookups", lookups);
		HttpSession session = request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		User user = (User) session.getAttribute("user");
		
		String email=null;
		if(user!=null)
		{
			email= user.getEmail();
			
		}
		Map<Integer, Integer> map = (Map<Integer, Integer>) session.getAttribute("card"+email);
		Map<Integer, String> wishlistMap = (Map<Integer, String>) session.getAttribute("wishlist"+email);
		
		int quantity=0;
		if(map!=null)
		{
			for(int value:map.values())
			{
				quantity+=value;
			}
		}
		
		int wishlistQuantity=0;
		if(wishlistMap!= null)
		{
			for(String value:wishlistMap.values())
			{
				if(value.equals("yes"))
					wishlistQuantity+= 1;
			}
		}
		
		model.addAttribute("quantity", quantity);	
		model.addAttribute("wishlistQuantity", wishlistQuantity);
		String page = request.getParameter("page");
		int pageNo=page!= null? Integer.parseInt(page):0;
		Page<Item> allItems = itemService.getAllItems(pageNo);
		System.out.println("Page No: "+pageNo);
		List<Item> list = allItems.getContent();
		List<Item> items = new ArrayList<>();
		for (Item item : list) {
			byte[] image = item.getImage();
			byte[] image1= item.getImage1();
			String cid = request.getParameter("cid"); // cid for lookup
			String search = request.getParameter("search");
			String item_data=item.getBrand()+" "+item.getName()+" "+item.getPrice()+" "+ item.getDescription()+ " "+ item.getDiscount();
			
			if (image != null) {
			
				String imagePath = Base64.getEncoder().encodeToString(image);
				item.setImagePath(imagePath);
				
			}
			if (image1 != null) {
				
				String imagePath1 = Base64.getEncoder().encodeToString(image1);
				item.setImagePath1(imagePath1);
				
			}
				// --------cart--------
				if(map!=null)
				{
					item.setTotal_quantity(map.getOrDefault(item.getId(), 0));
				}
				else
					item.setTotal_quantity(0);
				
				
				//-------- wishist---------
				if(wishlistMap!=null)
				{
					item.setHeart(wishlistMap.getOrDefault(item.getId(), "no"));
				}
				else
					item.setHeart("no");
				
				//category
				if(cid!=null && item.getCategory()==Integer.parseInt(cid))
				{
					items.add(item);
				}
				// search
				if(search!= null && item_data!= null)
				{
				item_data= item_data.toLowerCase();
				search= search.toLowerCase();
				}
				if(cid==null)
				{
					if(search!= null  && item_data.contains(search))
					{
						items.add(item);
					}
					
					if(cid== null  &&  search==null)
					{
						items.add(item);
					}
				}
				
			
		}
		model.addAttribute("user", user);
		model.addAttribute("items", items);
		model.addAttribute("allItems", allItems);
		return "home";
	}

	//-----------------------Logout User---------------------------
	@GetMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		session.removeAttribute("user");
		return "redirect:/home";
	}
	
	//----------------------- Send otp To Mail---------------------------
	@PostMapping("sendotp")
	public String sendotptomail(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		int otp = EmailSender.generateOtp();
		session.setAttribute("otp", otp);
		String email = request.getParameter("email");
		if (email != null) {
			System.out.println("send otp");
			
			session.setAttribute("email", email);
		}
		email=(String) session.getAttribute("email");
		session.setAttribute("otpCreationTime", LocalDateTime.now());
		boolean existsByEmail = userService.isExistsByEmail(email);
		if(!existsByEmail)
		{
			return "redirect:/login?emailError=please Enter valid Email. ";
		}else
		{
			User user = userService.getUserByEmail(email);
			
			EmailSender.sendOtpEmail(otp, user);
			System.out.println("OTP: " + otp);
			return "redirect:/resetpassword?send_OTP=OTP is send,please check your email.";

		}
			}

	//----------------------- Reset Password---------------------------
	@GetMapping("resetpassword")
	public String resetpassword(HttpServletRequest request, Model model, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		String checkotp = (String) session.getAttribute("checkotp");
		model.addAttribute("checkotp", checkotp);
		//session.removeAttribute("checkotp");
		model.addAttribute("send_OTP",request.getParameter("send_OTP"));
		model.addAttribute("missMatchPwdErr", request.getParameter("missMatchPwdErr"));
		
		//otp expire time display
		
		LocalDateTime localDateTime = (LocalDateTime) session.getAttribute("otpCreationTime");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		if(localDateTime!=null) {
			String formattedDateTime = localDateTime.format(formatter);
			model.addAttribute("formattedDateTime", formattedDateTime);
		}
		return "resetpasswordform";
	}

	// //----------------------- Check Otp---------------------------
	@PostMapping("checkotp")
	public String checkotp(HttpServletRequest request, Model model, HttpServletResponse response) {

		String strotp = request.getParameter("otp");
		HttpSession session = request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		boolean isOtpExpire = EmailSender.isOtpExpire(session);
		if (strotp != "") {
			int otp = Integer.parseInt(strotp);
			if (session.getAttribute("otp") != null) {
				int emailOtp = (int) session.getAttribute("otp");

				if (otp == emailOtp) {
					session.setAttribute("checkotp", "True");
					System.err.println("otp Matched");
					return "redirect:/resetpassword";
				}
			}

			// System.out.println("otp= "+otp);
		}
		System.out.println(" Is otp Expired?: " + isOtpExpire);
		System.out.println("OTP not matched");
		if(isOtpExpire)
		{
			model.addAttribute("otpError", "OTP Expire please resend OTP");
		}
		else
		model.addAttribute("otpError", "please enter correct OTP");
		
		//otp expire time display
		LocalDateTime localDateTime = (LocalDateTime) session.getAttribute("otpCreationTime");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		if(localDateTime!=null) {
			String formattedDateTime = localDateTime.format(formatter);
			model.addAttribute("formattedDateTime", formattedDateTime);
		}
		return "resetpasswordform";
	}

	//<----------------------------Change Password---------------------------->
	@PostMapping("changePassword")
	public String changePassword(HttpServletRequest request, HttpServletResponse response)
	{
		String newpassword = request.getParameter("newPassword");
		String confirmpassword = request.getParameter("confirmPassword");
		HttpSession session = request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		boolean changePassword = userService.changePassword(newpassword, confirmpassword, session);
		if(!changePassword)
		{
			System.out.println("New Password & Confirm Password Not Match");
			
			return "redirect:/resetpassword?missMatchPwdErr= Password Not Matched.";
		}
		session.removeAttribute("checkotp");
		return "redirect:/login";
	}
	
 @GetMapping("userData") 
 public List<User> api()
 {
	 List<User> list= new ArrayList<>();
	 list.add(new User());
	 list.add(new User());
	 list.add(new User()); 
	 return list ;
 }
 
 //--------------------------------- Profile Page--------------------------------
@GetMapping("profile")
 public String profile(HttpServletRequest request )
{
	
	return "profile";
}
}
