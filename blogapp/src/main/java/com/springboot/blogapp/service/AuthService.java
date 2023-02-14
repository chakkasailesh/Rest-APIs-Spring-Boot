package com.springboot.blogapp.service;

import com.springboot.blogapp.dto.LoginDTO;

public interface AuthService {
	String login(LoginDTO loginDTO);
}
