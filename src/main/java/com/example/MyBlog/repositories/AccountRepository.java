package com.example.MyBlog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MyBlog.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
	Account findByUsername(String username);
	Account save(Account account);
}
