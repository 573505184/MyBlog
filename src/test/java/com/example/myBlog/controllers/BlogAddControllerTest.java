package com.example.myBlog.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.MyBlog.models.Account;
import com.example.MyBlog.services.AccountService;
import com.example.MyBlog.services.BlogService;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogAddControllerTest {
	@MockBean
	private AccountService accountService;
	
	@MockBean
	private BlogService blogService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void prepareData() {
		Account account = new Account("Alice", "123@qq.com", "123");
		List<Account> accounts = List.of(new Account ("Alice","1234@qq.com", "ABC12345"));
		when(accountService.findByUsername(any())).thenReturn(account);
		when(blogService.createBlog(eq(account), any(), any())).thenReturn(true);
		when(accountService.findAll()).thenReturn(accounts);
		when(accountService.validateAccount(any(), any())).thenReturn(false);
		when(accountService.validateAccount("Alice", "ABC12345")).thenReturn(true);
	}
	
	
	@Test
	public void testGetBlogAddPage_Succeed() throws Exception{
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("123")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/blogadd")
				.with(user("Alice"))
				.with(csrf());
		
		mockMvc.perform(request)
				.andExpect(view().name("Blog-Add.html"));
	}
	
	@Test
	public void testBlogAdd_CorrectInfo_Succeed() throws Exception{
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("123")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/blogadd")
				.with(csrf())
				.with(user(alice))
				.param("title", "??????")
				.param("content", "????????????");
		
		mockMvc.perform(request)
				.andExpect(view().name("redirect:/myblog"));
	}
	
	@Test
	public void testGetBlogPage_Succeed() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/blog")
				.with(csrf());
		
		mockMvc.perform(request)
				.andExpect(view().name("Blog.html"));
	}
	
	@Test
	public void testMyBlog_Succeed() throws Exception{
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("123")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/myblog")
				.with(csrf())
				.with(user(alice));
		
		mockMvc.perform(request)
				.andExpect(view().name("Blog-User.html"));
	}
	
}
