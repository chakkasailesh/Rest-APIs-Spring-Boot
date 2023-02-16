package com.springboot.blogapp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.blogapp.dto.LoginDTO;
import com.springboot.blogapp.dto.RegisterDTO;
import com.springboot.blogapp.entity.Role;
import com.springboot.blogapp.entity.User;
import com.springboot.blogapp.exception.UserNameOrEmailExistsException;
import com.springboot.blogapp.repository.RoleRepository;
import com.springboot.blogapp.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String login(LoginDTO loginDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDTO.getUserNameOrEmail(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "User logged in successfully";
	}

	@Override
	public String register(RegisterDTO registerDTO) {
		if (userRepository.existsByUserName(registerDTO.getUserName()))
			throw new UserNameOrEmailExistsException("UserName");
		if (userRepository.existsByEmail(registerDTO.getEmail()))
			throw new UserNameOrEmailExistsException("Email");
		User user = new User();
		user.setName(registerDTO.getName());
		user.setUserName(registerDTO.getUserName());
		user.setEmail(registerDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
		return "User registered successfully";
	}

}
