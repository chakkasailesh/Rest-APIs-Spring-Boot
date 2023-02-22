package com.springboot.blogapp.dto;

import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
	private long id;
	private String name;
	private String description;
	private List<PostDTO> posts = new LinkedList<>();
}
