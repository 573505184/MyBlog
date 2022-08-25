package com.example.MyBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MyBlog.models.Blog;

	@Repository
	public interface BlogRepository extends JpaRepository<Blog,Long> {
		Blog findByTitle(String title);
		Blog save(Blog blog);
	}
