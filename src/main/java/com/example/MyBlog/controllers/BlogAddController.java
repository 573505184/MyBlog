package com.example.MyBlog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyBlog.models.Account;
import com.example.MyBlog.services.AccountService;
import com.example.MyBlog.services.BlogService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BlogAddController {
	@Autowired
	BlogService blogService;

	@GetMapping("/addblog")
	public String getaddblog() {
		return "Blog-Add.html";
	}
	
//	@GetMapping("/hello")
//	public ModelAndView gethello(ModelAndView mav) {
//		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		
//		
//		mav.addObject("name",user.getUsername());
//		
//		log.debug("WARN");
////		log.warn("WARN");	
//		
//		mav.setViewName("Blog.html");
//		return mav;
//	}
	
	@PostMapping("/addblog")
	public ModelAndView login(@RequestParam Account account,@RequestParam String title, @RequestParam String content, ModelAndView mav) {
		if (blogService.createBlog(account,title, content)) {
			mav.addObject("name", title);
			mav.setViewName("Blog-User.html");
		} else {
			mav.addObject("error", true);
			mav.setViewName("Blog-Add.html");
		}
		return mav;
		
	}
	
}

