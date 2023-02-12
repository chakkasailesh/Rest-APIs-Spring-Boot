package com.springboot.blogapp.service;

import java.util.List;

import com.springboot.blogapp.dto.CommentDTO;

public interface CommentService {
	CommentDTO createComment(long postId, CommentDTO commentDTO);

	List<CommentDTO> getComments(long postId);

	CommentDTO getComment(long postId, long commentId);

	CommentDTO updateComment(long postId, long commentId, CommentDTO commentDTO);

	void deleteComment(long postId, long commentId);
}
