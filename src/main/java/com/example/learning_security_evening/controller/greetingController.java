package com.example.learning_security_evening.controller;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class GreetingController {	
@GetMapping("/buyer/hello")

	public String buyerHello(@AuthenticationPrincipal UserDetails user) {
        return "Hello Buyer: " + user.getUsername(); 
		
        }

@GetMapping("/admin/hello")
public String adminHello(@AuthenticationPrincipal UserDetails user) {
	return "Hello Admin:" + user.getUsername();
	
  }

}
	
	
	 


