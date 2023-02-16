package com.springboot.blogapp.dto;

import lombok.Data;

@Data
public class RegisterDTO {
	private String name;
	private String userName;
	private String email;
	private String password;
}
