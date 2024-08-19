package com.ecommerce.controller;

import com.ecommerce.config.JwtUtil;
import com.ecommerce.config.UserDetailServiceImp;
import com.ecommerce.request.JwtRequest;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImp userDetailServiceImp;


//    @Hidden
    @Operation(summary = "Authenticate a user", description = "Authenticate user and generate a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated and token generated"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public String login(@RequestBody JwtRequest jwtRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (UsernameNotFoundException e){
            System.err.println("userNotFountExceptin in AuthenticationControole");
            e.printStackTrace();
            throw new UsernameNotFoundException("User Not Found Exception");
        }


        UserDetails userDetails = this.userDetailServiceImp.loadUserByUsername(jwtRequest.getEmail());

        return jwtUtil.generateToken(userDetails);
    }

}
