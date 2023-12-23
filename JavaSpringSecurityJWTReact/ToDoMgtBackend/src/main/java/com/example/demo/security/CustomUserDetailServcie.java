package com.example.demo.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CustomUserDetailServcie implements UserDetailsService{

	
	UserRepo repo;
	
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user =  repo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
		
	Set<GrantedAuthority> authorities = user.getRoles().stream()
			.map((role)-> new SimpleGrantedAuthority(role.getName()))
			.collect(Collectors.toSet());
				
	return new org.springframework.security.core.userdetails.User(usernameOrEmail, user.getPassword(),authorities);
	}

}
