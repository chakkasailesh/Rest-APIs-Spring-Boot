package com.springboot.blogapp.service;

import com.springboot.blogapp.dto.PostDTO;

public interface PostService {
	PostDTO createPost(PostDTO postDTO);
}
