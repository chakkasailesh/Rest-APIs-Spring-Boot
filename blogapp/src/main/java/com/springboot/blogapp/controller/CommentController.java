package com.springboot.blogapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogapp.dto.CommentDTO;
import com.springboot.blogapp.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentController {
	@Autowired
	private CommentService commentService;

	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@PathVariable long postId,
			@Valid @RequestBody CommentDTO commentDTO) {
		return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
	}

	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity<List<CommentDTO>> getComments(@PathVariable long postId) {
		return new ResponseEntity<>(commentService.getComments(postId), HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDTO> getComment(@PathVariable long postId, @PathVariable long commentId) {
		return new ResponseEntity<>(commentService.getComment(postId, commentId), HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable long postId, @PathVariable long commentId,
			@Valid @RequestBody CommentDTO commentDTO) {
		return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDTO), HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable long postId, @PathVariable long commentId) {
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
	}
}
