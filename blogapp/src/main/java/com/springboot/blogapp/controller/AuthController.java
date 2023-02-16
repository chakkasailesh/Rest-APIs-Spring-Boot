package com.springboot.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.blogapp.dto.LoginDTO;
import com.springboot.blogapp.dto.RegisterDTO;
import com.springboot.blogapp.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping(value = { "/login", "/signin" })
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
		return ResponseEntity.ok(authService.login(loginDTO));
	}

	@PostMapping(value = { "/signup", "/register" })
	public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
		return ResponseEntity.ok(authService.register(registerDTO));
	}
}
