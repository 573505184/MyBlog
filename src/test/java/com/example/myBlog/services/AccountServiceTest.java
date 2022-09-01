package com.example.myBlog.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.MyBlog.models.Account;
import com.example.MyBlog.repositories.AccountRepository;
import com.example.MyBlog.services.AccountService;


@SpringBootTest
public class AccountServiceTest {
	@MockBean
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountService accountService;
	
	@BeforeEach
	public void prepareData() {
		Account alice = new Account("Alice","123@qq.com","ABC12345");
		
		when(accountRepository.save(any())).thenReturn(null);
		when(accountRepository.findByUsername(any())).thenReturn(null);
		when(accountRepository.findByUsername("Alice")).thenReturn(alice);
//		List<Account> accounts = List.of(new Account ("Alice","1234@qq.com", "ABC12345"));
		when(accountService.findAll()).thenReturn(null);
		when(accountService.findByUsername(null)).thenReturn((null));
	}

	
	@Test
	public void testValidateAccount_CorrectInfo_ReturnTrue() {
		assertTrue(accountService.validateAccount("Alice", "ABC12345"));
	}
	
	@Test
	public void testValidateAccount_WrongUsername_ReturnFalse() {
		assertFalse(accountService.validateAccount("Bob", "Bob54321"));
	}
	
	@Test
	public void testValidateAccount_WrongPassword_ReturnFalse() {
		assertFalse(accountService.validateAccount("Alice", "BBC12321"));
	}
	
	@Test
	public void testCreateAccount_NewUsername_ReturnTrue() {
		assertTrue(accountService.createAccount("Bob","qqq@qq.com","Bob54321"));
	}
	
	@Test
	public void testCreateAccount_ExistingUsername_ReturnFalse() {
		assertFalse(accountService.createAccount("Alice","www@ww.com","BBC12321"));
	}
}
