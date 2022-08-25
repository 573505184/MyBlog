package com.example.MyBlog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MyBlog.models.Account;
import com.example.MyBlog.repositories.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository repository;

	public boolean validateAccount(String username,String password) {
		Account account = repository.findByUsername(username);
		if (account == null || !account.getPassword().equals(password)) {
			return false;
		} else {
			return true;
		}
	}

	
	public boolean createAccount(String username,String email,String password) {
		if(repository.findByUsername(username) == null) {
			repository.save(new Account(username,email,password));
			return true;
		}else{
			return false;
		}
	}
}
