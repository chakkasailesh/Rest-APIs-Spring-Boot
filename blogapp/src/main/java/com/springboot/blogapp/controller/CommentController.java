package com.springboot.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogapp.dto.CommentDTO;
import com.springboot.blogapp.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@PathVariable long postId, @RequestBody CommentDTO commentDTO) {
		return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
	}
}
