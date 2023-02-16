package com.springboot.blogapp.service;

import com.springboot.blogapp.dto.LoginDTO;
import com.springboot.blogapp.dto.RegisterDTO;

public interface AuthService {
	String login(LoginDTO loginDTO);

	String register(RegisterDTO registerDTO);
}
