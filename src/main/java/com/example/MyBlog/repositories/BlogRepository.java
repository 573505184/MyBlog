package com.example.MyBlog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MyBlog.models.Account;
import com.example.MyBlog.models.Blog;

	@Repository
	public interface BlogRepository extends JpaRepository<Blog,Long> {
		Blog findByTitle(String title);
		Blog save(Blog blog);
		void deleteById(Long id);
		List<Blog> findAllByAccount(Account account);
	}
