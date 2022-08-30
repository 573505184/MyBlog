package com.example.MyBlog.services;

import java.util.List;

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

	// 创建新博客
	public boolean createBlog(Account account, String title, String content) {
		repository.save(new Blog(account, title, content));
		return true;
	}

	// 获取所有博客内容
	public List<Blog> findAll() {
		return repository.findAll();
	}
	
//	//获取用户博客内容
//	public List<Blog> findByUsername(String username) {
//		return repository.findByUsername(username);
//	}

	// 删除博客
	public boolean deleteById(Long id) {
		return true;
	}

	public List<Blog> findByAccount(Account account) {
		return repository.findAllByAccount(account);
	}
	
}
