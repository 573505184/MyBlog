package com.example.MyBlog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.MyBlog.models.Account;
import com.example.MyBlog.services.AccountService;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
	@Autowired
	AccountService accountService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/register", "/css/**", "/images/**", "/blog").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();
	}

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		List<Account> accounts = accountService.findAll();
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		for (Account account : accounts) {
			UserDetails user = User.withDefaultPasswordEncoder().username(account.getUsername())
					.password(account.getPassword()).roles("USER").build();
			manager.createUser(user);
		}

		return manager;
	}
}
