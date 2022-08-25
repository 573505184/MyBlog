package com.example.MyBlog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MyBlog.models.Account;
import com.example.MyBlog.models.Blog;
import com.example.MyBlog.repositories.AccountRepository;
import com.example.MyBlog.repositories.BlogRepository;

@Service
public class BlogService {
	@Autowired
	BlogRepository repository;

	public boolean validateBlog(Account account,String title, String content) {
		return true;
	}

	public boolean createBlog(Account account,String title, String content) {
		repository.save(new Blog(account,title, content));
		return true;
	}
}
