package com.springboot.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogapp.dto.JWTAuthResponse;
import com.springboot.blogapp.dto.LoginDTO;
import com.springboot.blogapp.dto.RegisterDTO;
import com.springboot.blogapp.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping(value = { "/login", "/signin" })
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDTO) {
		String token = authService.login(loginDTO);
		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		return ResponseEntity.ok(jwtAuthResponse);
	}

	@PostMapping(value = { "/signup", "/register" })
	public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
		return ResponseEntity.ok(authService.register(registerDTO));
	}
}
