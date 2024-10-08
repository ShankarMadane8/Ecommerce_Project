package com.ecommerce.config;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailServiceImp userDetailServiceImp;
	
	@Autowired
	private JwtUtil jwtUtil;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestTokenHeader = request.getHeader("Authorization");
//		System.out.println(requestTokenHeader);
		String userName=null;
		String jwtToken=null;
		
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
			
			jwtToken=requestTokenHeader.substring(7);
			
			try {
				
				userName=this.jwtUtil.extractUsername(jwtToken);
				
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
				System.err.println("jwt Token has Expired........");
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("error");
			}
			
		}else {
			System.out.println("invalid token , not start with bearer string ");
		}
		
		

		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			final UserDetails userDetails = userDetailServiceImp.loadUserByUsername(userName);
			if(this.jwtUtil.validateToken(jwtToken, userDetails)) {
				
				//token is valid
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthention=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());			
				usernamePasswordAuthention.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthention);
				
			}else {
				System.out.println("token is invalid");
			}
		
		}else {
			System.out.println("user name is null");
		}

		filterChain.doFilter(request, response);
		
		
	}
	
	

}
