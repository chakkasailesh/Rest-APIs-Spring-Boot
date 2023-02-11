package com.springboot.blogapp.service;

import com.springboot.blogapp.dto.CommentDTO;

public interface CommentService {
	CommentDTO createComment(long postId, CommentDTO commentDTO);
}
