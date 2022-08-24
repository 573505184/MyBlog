package com.example.MyBlog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyBlog.services.AccountService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	@Autowired
	AccountService accountService;
	
	@GetMapping("/login")
	public String getlogin() {
		return "Login.html";
	}
	
	@GetMapping("/hello")
	public ModelAndView gethello(ModelAndView mav) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		mav.addObject("name",user.getUsername());
		
		log.debug("WARN");
//		log.warn("WARN");	
		
		mav.setViewName("Blog.html");
		return mav;
	}
	
//	@PostMapping("/login")
//	public ModelAndView login(@RequestParam String username, @RequestParam String password, ModelAndView mav) {
//		if (accountService.validateAccount(username, password)) {
//			mav.addObject("name", username);
//			mav.setViewName("hello.html");
//		} else {
//			mav.addObject("error", true);
//			mav.setViewName("Login1.html");
//		}
//		return mav;
//		
//	}
	
}
