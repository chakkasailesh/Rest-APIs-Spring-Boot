package com.springboot.blogapp.service;

import java.util.List;

import com.springboot.blogapp.dto.PostDTO;

public interface PostService {
	PostDTO createPost(PostDTO postDTO);

	List<PostDTO> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

	PostDTO getPost(long id);

	PostDTO updatePost(long id, PostDTO postDTO);

	void deletePost(long id);

	List<PostDTO> getPostsByCategory(long categoryId);
}
