package com.example.MyBlog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyBlog.services.AccountService;

@Controller
public class RegisterController {

	@Autowired
	AccountService accountService;
	
	@GetMapping("/register")
	public String getregister() {
		return "Register.html";
	}
	
	
	@PostMapping("/register")
	public ModelAndView login(@RequestParam String username,@RequestParam String email,@RequestParam String password, ModelAndView mav) {
		if (accountService.createAccount(username,email,password)) {
			mav.addObject("error", false);
			mav.setViewName("Login.html");
		} else {
			mav.addObject("error", true);
			mav.setViewName("Register.html");
		}
		return mav;
	}
}
