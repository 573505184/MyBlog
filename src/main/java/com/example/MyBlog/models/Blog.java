package com.example.MyBlog.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Blog {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	
	public Account getAccount() {
		return account;
	}

	@Column
	private String title;
	
	@Column
	private String content;

	private Account account;
	
	public Blog(Account account,String title,String content) {
		this.account = account;
		this.title = title;
		this.content = content;
	}
	
	public Blog() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
}
