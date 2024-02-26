package com.Ecommerce.Controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;

import com.Ecommerce.Entity.Address;

import com.Ecommerce.Entity.Item;
import com.Ecommerce.Entity.OrderItem;
import com.Ecommerce.Entity.Payment;
import com.Ecommerce.Entity.PaymentProcessingDetails;
import com.Ecommerce.Entity.RatingReview;
import com.Ecommerce.Entity.User;
import com.Ecommerce.Service.AddressServiceImple;
import com.Ecommerce.Service.CartServiceImpl;
import com.Ecommerce.Service.ItemServiceImpl;
import com.Ecommerce.Service.OrderHdServiceImpl;
import com.Ecommerce.Service.OrderItemServiceImpl;
import com.Ecommerce.Service.PaymentServiceImpl;
import com.Ecommerce.Service.RatingReviewServiceImpl;
import com.Ecommerce.ServiceInterfaces.AddressService;

import ch.qos.logback.core.ConsoleAppender;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.Ecommerce.Entity.Cart;
@Controller
public class CartController {
	@Autowired
	ItemServiceImpl itemService;
	
	@Autowired
	RatingReviewServiceImpl ratingReviewService;
	
	@Autowired
	AddressServiceImple addressServiceImple;
	
	@Autowired
	OrderHdServiceImpl orderHdService;
	
	@Autowired
	CartServiceImpl cartService;
	
	@Autowired
	OrderItemServiceImpl orderItemService;
	
	@Autowired
	PaymentServiceImpl paymentService;
	
	//----------------------- Add To Cart---------------------------
	@PostMapping("addToCart")
	 String addToCart(HttpServletRequest request, HttpServletResponse response)
	{
	
		System.err.println("I am here:"+request.getParameter("cartType"));
		HttpSession session = request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		User user = (User) session.getAttribute("user");
		String email=null;
		String pageName = request.getParameter("page");
		if(user!= null)
		{
			email=user.getEmail();
			Map<Integer, Integer> map = (Map<Integer, Integer>) session.getAttribute("card"+email);
			map=(map==null)?new HashMap<>():map;
			int item_id =Integer.parseInt(request.getParameter("item_id"));
			map.put(item_id, map.getOrDefault(item_id, 0)+1);
			// ----------------------- Add item to cart table------------------------------
			Cart cart=new Cart();
			String cartType = request.getParameter("cartType");
			cart.setCustomerId(user.getId());
			cart.setItemId(item_id);
			Item item = itemService.getItem(item_id);
			cart.setPrice(item.getPrice());
			cart.setQuantity(map.get(item_id));
			
			cart.setType(cartType);
			session.setAttribute("cartType", cart.getType());
			String msg = cartService.saveCartItem(cart);
			System.err.println(msg);
			System.out.println(map+" "+item_id);
			session.setAttribute("card"+email, map);
			if(cartType!=null &&cartType.equals("buyNow"))
			{
				return "redirect:/address?cartType="+cartType+"&itemId="+item_id;
			}
			return "redirect:/"+pageName;
			
		}
		else
			return "redirect:/home?alert=true";
		
	}
	@PostMapping("Buynow")
	public String Buynow(HttpServletRequest request, HttpServletResponse response)
	{
		
		System.err.println("I am here:"+request.getParameter("type"));
		String sitem_id =request.getParameter("item_id");
		HttpSession session = request.getSession();
		session.setAttribute("Buynow",  sitem_id);
		
	    
		String pageName = request.getParameter("page");
		System.out.println("Buynow="+sitem_id+"pageName="+pageName);
		return "redirect:/"+pageName;
		
		//return sitem_id;
	}
	

	//---------------------------Remove from Cart---------------------------
	@PostMapping("removeFromCart")
	public String removeFromCart(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		User user = (User) session.getAttribute("user");
		String email=null;
		if(user!= null)
		{
			
			email=user.getEmail();
			Map<Integer, Integer> map1 = (Map<Integer, Integer>) session.getAttribute("card"+email);
			String pageName = request.getParameter("page");
			/*
			if(map1!= null)
			{
				int item_id = Integer.parseInt(request.getParameter("item_id"));
				if(map1.containsKey(item_id))
				{
					int quantity1 = map1.get(item_id);
					if(quantity1>1)
					{
						map1.put(item_id, quantity1-1);
					}
					else {
		                // Remove the item from the cart if its quantity is 1
		                map1.remove(item_id);
		            }
					System.out.println(map1+" "+item_id);
					session.setAttribute("card"+email, map1);
					
				}
			}*/
			
			int item_id = Integer.parseInt(request.getParameter("item_id"));
			map1.put(item_id, map1.get(item_id)-1);
			
			// ----------------------- remove item to cart table------------------------------
			Cart cart=new Cart();
			
			cart.setCustomerId(user.getId());
			cart.setItemId(item_id);
			Item item = itemService.getItem(item_id);
			cart.setPrice(item.getPrice());
			
			if(map1!= null &&map1.get(item_id)==0 )
			{
				map1.remove(item_id);
			}
			cart.setQuantity(map1.getOrDefault(item_id,0));
			String msg = cartService.saveCartItem(cart);
			System.err.println(msg);
			session.setAttribute("card"+email, map1);
			return "redirect:/"+pageName;
		}	
		
		return "redirect:/home?alert=true";
	}
	
	//-----------------------addToWishlist--------------------------
	@PostMapping("addToWishlist")
	public String addToWishlist(HttpServletRequest request, HttpServletResponse response)
	{
		
		HttpSession session=request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		User user = (User) session.getAttribute("user");
		String email=null;
		if(user!= null)
		{
			email=user.getEmail();
			Map<Integer, String> map = (Map<Integer, String>) session.getAttribute("wishlist"+email);
			map=(map==null)?new HashMap<>():map;
			int item_id =Integer.parseInt(request.getParameter("item_id"));
			map.put(item_id, "yes");
			System.out.println("Wishlist: "+map+" "+item_id);
			session.setAttribute("wishlist"+email, map);
			return "redirect:/home";
		}
		return "redirect:/home?alert=true";
	}
	
	
	//----------------------- Remove From Wishlist---------------------------
	@PostMapping("removeFromWishlist")
	public String removeFromWishlist(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		User user = (User) session.getAttribute("user");
		String pageName = request.getParameter("page");
		
			String email=user.getEmail();
			Map<Integer, String> map = (Map<Integer, String>) session.getAttribute("wishlist"+email);
			int item_id = Integer.parseInt(request.getParameter("item_id"));
			map.put(item_id,"no");
			session.setAttribute("wishlist"+email, map);
			return "redirect:/"+ pageName;
		
	}
	
	
	//------------------Get Cart Method-------------------
	@GetMapping("cart")
	public String Cart(HttpServletRequest request, Model model, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		List<Item> allItems = itemService.getAllItems();
		List<Item> items= new ArrayList<>(); 
		User user = (User) session.getAttribute("user");
		int total_Price=0;
		int total_Discount=0;
		int quantity=0;
		
		if(user!=null)
		{
			String email=user.getEmail();
			Map<Integer, Integer> map = (Map<Integer, Integer>) session.getAttribute("card"+email);
			
			if(map!=null)
			{
				
				// Total qauntity
					for(int value:map.values())
					{
						quantity+=value;
					}
				
				
				for(Item item:allItems)
				{
					if(map.containsKey(item.getId()))
					{
						byte[] image = item.getImage();
						//byte[] image1= item.getImage1();
						if (image != null ) {
							String imagePath = Base64.getEncoder().encodeToString(image);
							//String imagePath1 = Base64.getEncoder().encodeToString(image1);
							item.setImagePath(imagePath);
							//item.setImagePath1(imagePath1);
						}
						
						item.setTotal_quantity(map.get(item.getId()));
					//	total_Price+=(item.getPrice()-(item.getPrice()*(item.getDiscount()/100)))*item.getTotal_quantity();
						total_Price+=(item.getPrice()*item.getTotal_quantity());
						total_Discount+=(item.getPrice()*(item.getDiscount()/100))*item.getTotal_quantity();
						System.out.println((item.getPrice()*(item.getDiscount()/100)));
						session.setAttribute("totalAmount", total_Price-total_Discount);
						items.add(item);
					}
				} 
			}
		}
		
		//----------------------- Wishlist Map---------------------------
		int wishlistQuantity=0;
		if(user!= null)
		{
		Map<Integer, String> wishlistMap = (Map<Integer, String>) session.getAttribute("wishlist"+user.getEmail());
		
		if(wishlistMap!= null)
		{
			for(String value:wishlistMap.values())
			{
				if(value.equals("yes"))
					wishlistQuantity+= 1;
			}
		}
		}
		System.out.println(items);
		model.addAttribute("items", items);
		model.addAttribute("total_Price",total_Price);
		model.addAttribute("quantity", quantity);
		model.addAttribute("total_Discount",total_Discount);
		model.addAttribute("user", user);
		model.addAttribute("wishlistQuantity", wishlistQuantity);
		return "cart";
		
	}
	
	
	//----------------------- item Page---------------------------
	@GetMapping("/item/{id}")
	public String itemDetail(@PathVariable("id") Integer id, Model model,HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		User user = (User) session.getAttribute("user");
		Item item = itemService.getItem(id);
		
		//----------------- Rating Review --------------------
		List<RatingReview> allRatingReviews = ratingReviewService.getAllRatReview();
		int star5=0, star4=0, star3=0, star2=0,star1=0;
		float avgRating;
		float totalRatingUser=0;
		for(RatingReview ratingReview:allRatingReviews)
		{
			if(ratingReview.getProductId()==id)
			{
				totalRatingUser+=1;
				if(ratingReview.getRating()==5)
				{
					star5+=1;
				}
				if(ratingReview.getRating()==4)
				{
					star4+=1;
				}
				if(ratingReview.getRating()==3)
				{
					star3+=1;
				}
				if(ratingReview.getRating()==2)
				{
					star2+=1;
				}
				if(ratingReview.getRating()==1)
				{
					star1+=1;
				}
				
			}
			
		}
		avgRating=((5*star5)+(4 * star4)+ (3 * star3) +(2 * star2)+(1 * star1))/totalRatingUser;
		System.out.println("Star 5= "+star5);
		System.out.println("Star 4= "+star4);
		System.out.println("Star 3= "+star3);
		System.out.println("Star 2= "+star2);
		System.out.println("Star 1= "+star1);
		System.out.println("total Rating User= "+totalRatingUser);
		System.out.println("Avearge Rating= "+avgRating);
		
		System.out.println(id);
		byte[] image = item.getImage();
		
		if (image != null ) {
			String imagePath = Base64.getEncoder().encodeToString(image);
			
			item.setImagePath(imagePath);
		}
		
		//----------------------- Wishlist Navbar---------------------------
		int wishlistQuantity=0;
		int quantity=0;
		if(user!= null)
		{
		Map<Integer, String> wishlistMap = (Map<Integer, String>) session.getAttribute("wishlist"+user.getEmail());
		
		if(wishlistMap!= null)
		{
			for(String value:wishlistMap.values())
			{
				if(value.equals("yes"))
					wishlistQuantity+= 1;
			}
		}
		//------------------- Cart Quantity in Navbar----------------
		String email=user.getEmail();
		Map<Integer, Integer> map = (Map<Integer, Integer>) session.getAttribute("card"+email);
		if(map!=null)
		{
		for(int value:map.values())
		{
			quantity+=value;
		}
		}
		}
		
		
		model.addAttribute("item", item);
		model.addAttribute("star5", star5);
		model.addAttribute("star4", star4);
		model.addAttribute("star3", star3);
		model.addAttribute("star2", star2);
		model.addAttribute("star1", star1);
		model.addAttribute("avgRating", avgRating);
		model.addAttribute("totalRatingUser", totalRatingUser);
		model.addAttribute("user", user);
		model.addAttribute("wishlistQuantity", wishlistQuantity);
		model.addAttribute("quantity", quantity);
		return "itemDetails";
	}
	
	//----------------------------- Wishlist Page-----------------------------------
	@GetMapping("wishlist")
	public String wishlist(HttpServletRequest request, Model model, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		List<Item> allItems = itemService.getAllItems();
		List<Item> items= new ArrayList<>(); 
		User user = (User) session.getAttribute("user");
		int wishlistQuantity=0; 
		int quantity=0;
		if(user!= null)
		{
			String email=user.getEmail();
			Map<Integer, Integer> map = (Map<Integer, Integer>) session.getAttribute("card"+email);
			
			// Total qauntity
			for(int value:map.values())
			{
				quantity+=value;
			}
			
			Map<Integer, String> wishlistMap = (Map<Integer, String>) session.getAttribute("wishlist"+user.getEmail());
			if(wishlistMap != null)
			{
				for(Item item:allItems)
				{
					if(wishlistMap.containsKey(item.getId()) && wishlistMap.get(item.getId()).equals("yes"))
					{
						byte[] image = item.getImage();
						//byte[] image1= item.getImage1();
						if (image != null ) {
							String imagePath = Base64.getEncoder().encodeToString(image);
							//String imagePath1 = Base64.getEncoder().encodeToString(image1);
							item.setImagePath(imagePath);
							//item.setImagePath1(imagePath1);
							
							
							items.add(item);
						}
					}
				}
			}
		
			if(wishlistMap!= null)
			{
				for(String value:wishlistMap.values())
				{
					if(value.equals("yes"))
						wishlistQuantity+= 1;
				}
			}
		
		}
		
		model.addAttribute("user", user);
		model.addAttribute("items", items);
		model.addAttribute("wishlistQuantity", wishlistQuantity);
		model.addAttribute("quantity", quantity);
		return "wishlist";
		
	}
	
	
	//----------------------------- Address page-----------------------------------

	@GetMapping("address")
	public String address(HttpServletRequest request, Model model, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//String cartType = (String) session.getAttribute("cartType");
		String cartType = request.getParameter("cartType");
		String itemId = request.getParameter("itemId");
		System.err.println(cartType);
		List<com.Ecommerce.Entity.Cart> list = cartService.findAllByCustomerId(user.getId());
		if(cartType!= null)
		{
			
			System.out.println("Cart Item fetched using cart type");
		int customerId = user.getId();
		 list = cartService.findByCustomerIdAndType(customerId, cartType);
		}
		
		
		orderItemService.saveOrder(request, list);
		
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		//User user = (User) session.getAttribute("user");
		if(user==null)
		{
			return "redirect:/login";
		}
		//---------------------Addresses-----------------------------
		List<Address> addresses = addressServiceImple.getAddressesByUserId(user.getId());
		// order summary
				List<Item> allItems = itemService.getAllItems();
				List<OrderItem> orderItems = orderItemService.findAll();
				List<Item> items= new ArrayList<>(); 
				int total_Price=0;
				int total_Discount=0;
				int quantity=0;
				if(user!=null)
				{
					String email=user.getEmail();
					Map<Integer, Integer> map = (Map<Integer, Integer>) session.getAttribute("card"+email);
					
					if(map!=null)
					{
						
						// Total qauntity
							for(int value:map.values())
							{
								quantity+=value;
							}
						
						
						for(Item item:allItems)
						{
							if(map.containsKey(item.getId()))
							{
								byte[] image = item.getImage();
								//byte[] image1= item.getImage1();
								if (image != null ) {
									String imagePath = Base64.getEncoder().encodeToString(image);
									//String imagePath1 = Base64.getEncoder().encodeToString(image1);
									item.setImagePath(imagePath);
									//item.setImagePath1(imagePath1);
								}
								
								item.setTotal_quantity(map.get(item.getId()));
							//	total_Price+=(item.getPrice()-(item.getPrice()*(item.getDiscount()/100)))*item.getTotal_quantity();
								total_Price+=(item.getPrice()*item.getTotal_quantity());
								total_Discount+=(item.getPrice()*(item.getDiscount()/100))*item.getTotal_quantity();
								System.out.println((item.getPrice()*(item.getDiscount()/100)));
								for(OrderItem orderItem: orderItems)
								{
									if(orderItem.getItemId()==item.getId())
									{
										if(orderItem.getCartType()!=null &&   orderItem.getCartType().equals(cartType) && item.getId()==Integer.parseInt(itemId))
										{
											items.add(item);
										}
										
										if(cartType == null)
										{
											
											items.add(item);
										}
										System.err.println(cartType);
									}
								}
								
								System.out.println("item added in list");
							}
						} 
					}
				}
				System.out.println(items);
				model.addAttribute("total_Price",total_Price);
				model.addAttribute("quantity", quantity);
				model.addAttribute("total_Discount",total_Discount);
				model.addAttribute("user", user);
				model.addAttribute("items", items);
				model.addAttribute("addresses", addresses);
		return "address";
	
	}
	
	@PostMapping("/address/addAddress")
	public String addAddress(@ModelAttribute Address address, HttpServletRequest request, Model model, HttpServletResponse response)
	{
		
		HttpSession session = request.getSession();
		Cookie cookie= new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(60*60*24*10);
		response.addCookie(cookie);
		User user = (User) session.getAttribute("user");
		address.setUser_id(user.getId());
		String msg = addressServiceImple.saveAddress(address);
		System.out.println(msg);
		System.out.println(user.getId()+" "+ address.getUser_id());
		System.out.println(user);
		
		return "redirect:/address";
	
	}
	
	@PostMapping("paymentProcessingDetails")
	public String getPaymentProcessingDetails(HttpServletRequest request, @RequestBody PaymentProcessingDetails paymentProcessingDetails)
	{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
//		String addressid = request.getParameter("address");
//		String paymentMode = request.getParameter("paymentMode");
//		System.err.println("Payement procesing method working");
		System.err.println(paymentProcessingDetails);
		Address address = addressServiceImple.getAddressById(paymentProcessingDetails.getAddressId());
//		System.err.println(address);
//		System.err.println(addressid);
//		System.err.println(paymentMode);
		Payment payment=new Payment();
		payment.setCustomerId(user.getId());
		payment.setCardHolderName(paymentProcessingDetails.getCardHolderName());
		payment.setCardNo(paymentProcessingDetails.getCardNumber());
		payment.setCvv(paymentProcessingDetails.getCvv());
		int totalAmount = (int) session.getAttribute("totalAmount");
		payment.setAmount(totalAmount);
		System.err.println("Payment :"+payment);
		String msg = paymentService.savepayment(payment);
		
		EmailSender.paymentProcessingMail(payment, user, address);
		System.out.println(msg);
		return "redirect:/address";
		
	}
	
	
}
