package com.example.MyBlog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyBlog.models.Account;
import com.example.MyBlog.models.Blog;
import com.example.MyBlog.services.AccountService;
import com.example.MyBlog.services.BlogService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BlogAddController {
	@Autowired
	BlogService blogService;
	private String username;

	// 打开添加博客页面
	@GetMapping("/addblog")
	public String getaddblog() {

		return "Blog-Add.html";
	}

	// 添加博客
	@PostMapping("/addblog")
	public ModelAndView blogadd(@RequestParam Account account, @RequestParam String title, @RequestParam String content,
			ModelAndView mav) {
		if (blogService.createBlog(account, title, content)) {
			mav.addObject("name", title);
			mav.setViewName("Blog-User.html");
		} else {
			mav.addObject("error", true);
			mav.setViewName("Blog-Add.html");
		}
		return mav;
	}

	// 打开博客页面
	@GetMapping("/blog")
	public ModelAndView getblog(ModelAndView mav) {
		List<Blog> blogs = blogService.findAll();
		mav.addObject("blogs", blogs);
		mav.setViewName("Blog.html");
		return mav;
	}

	// 打开用户博客页面
	@GetMapping("/myblog")
	public ModelAndView getmyblog(ModelAndView mav) {
		List<Blog>blogs=blogService.findByUsername(username);
		mav.addObject("blogs", blogs);
		mav.setViewName("Blog-User.html");
		return mav;
	}

	// 根据登录名字找到blogs
//	@GetMapping("/blog")
//	public ModelAndView getblog(ModelAndView mav) {
////	List<Blog>blogs=blogService.findByUsername(username){
//	   mav.addObject("blogs", blogs);
//	   mav.setViewName("myblog");
//	  }else{
//		mav.addObject("error", true);
//		mav.setViewName("login");
//	}return mav;
}
