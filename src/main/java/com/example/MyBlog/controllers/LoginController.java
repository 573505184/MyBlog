package com.example.MyBlog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyBlog.models.Account;
import com.example.MyBlog.models.Blog;
import com.example.MyBlog.services.AccountService;
import com.example.MyBlog.services.BlogService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	@Autowired
	AccountService accountService;
	@Autowired
	BlogService blogService;

	@GetMapping("/login")
	public String getlogin() {
		return "Login.html";
	}

	
	@GetMapping("/")
	public String getindex() {
		return "redirect:/myblog";
	}

//	@PostMapping("/login")
//	public ModelAndView login(@RequestParam String username, @RequestParam String password, ModelAndView mav) {
//		if (accountService.validateAccount(username, password)) {
//			List<Blog>blogs=blogService.findAll();
//			mav.addObject("blogs", blogs);
//			mav.setViewName("Blog-User.html");
//		} else {
//			mav.addObject("error", true);
//			mav.setViewName("Login.html");
//		}
//		return mav;		
//	}

}
