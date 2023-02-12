package com.springboot.blogapp.dto;

import java.util.LinkedList;
import java.util.List;

import com.springboot.blogapp.entity.Post;

import lombok.Data;

@Data
public class PostDTO {
	private long id;
	private String title;
	private String description;
	private String content;
	private List<CommentDTO> comments = new LinkedList<>();

	public static PostDTO createPostDTO(Post post) {
		PostDTO postDTO = new PostDTO();
		postDTO.setId(post.getId());
		postDTO.setContent(post.getContent());
		postDTO.setTitle(post.getTitle());
		postDTO.setDescription(post.getDescription());
		return postDTO;
	}
}
