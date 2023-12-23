package com.example.demo.service;

import com.example.demo.dto.JwtAuthResponse;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;

public interface AuthService {

	String register(RegisterDto rdto);
	JwtAuthResponse login(LoginDto ldto);
	
}
