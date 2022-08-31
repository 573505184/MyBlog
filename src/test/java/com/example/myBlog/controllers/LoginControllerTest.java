package com.example.myBlog.controllers;

import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.MyBlog.models.Account;
import com.example.MyBlog.services.AccountService;



@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {	
	@MockBean
	private AccountService accountService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void prepareData() {
//		List<Account> accounts = List.of(new Account ("Alice","1234@qq.com", "ABC12345"));
//		when(accountService.findAll()).thenReturn(accounts);
		when(accountService.validateAccount(any(), any())).thenReturn(false);
		when(accountService.validateAccount("Alice", "ABC12345")).thenReturn(true);
	}
	
	@Test
	public void testGetLoginPage_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/login");
		
		mockMvc.perform(request)
				.andExpect(view().name("Login.html"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
	
	@Test
	public void testGetindex_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/")
				.with(user("Alice"))
				.with(csrf());	
		
		mockMvc.perform(request)
				.andExpect(redirectedUrl("/myblog"));
	}
	
	@Test
	public void testLogin_CorrectInfo_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/login")
				.with(csrf())
				.with(anonymous())
				.param("username", "Alice")
				.param("password", "ABC12345");
		
		mockMvc.perform(request)
				.andExpect(redirectedUrl("/"));
	}
	
	
	@Test
	public void testLogin_IncorrectInfo_Fail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/login")
				.with(csrf())
				.param("username", "Bob")
				.param("password", "Bob54321");
		
		mockMvc.perform(request)
				.andExpect(redirectedUrl("/login?error"));
	}
}

