package com.springboot.blogapp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.blogapp.entity.Role;
import com.springboot.blogapp.repository.RoleRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Blog App APIs", version = "1.0", description = "Blog Application APIs Information"))
public class BlogappApplication implements CommandLineRunner {
	@Autowired
	RoleRepository roleRepository;

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role admin = new Role();
		admin.setName("ROLE_ADMIN");
		roleRepository.save(admin);
		Role user = new Role();
		user.setName("ROLE_USER");
		roleRepository.save(user);
	}

}
