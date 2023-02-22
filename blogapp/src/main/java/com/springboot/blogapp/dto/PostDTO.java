package com.springboot.blogapp.dto;

import java.util.LinkedList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDTO {
	private long id;
	@NotBlank
	@Size(min = 3, message = "Post title should have minimum length 3")
	private String title;
	@NotBlank
	@Size(min = 5, message = "Post description should have minimum length 10")
	private String description;
	@NotBlank
	@Size(min = 5, message = "Post content should have minimum length 10")
	private String content;
	private List<CommentDTO> comments = new LinkedList<>();
	private long categoryId;
}
