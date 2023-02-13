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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogapp.dto.PostDTO;
import com.springboot.blogapp.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
		return new ResponseEntity<PostDTO>(postService.createPost(postDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<PostDTO>> getAllPosts(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
		return new ResponseEntity<>(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDTO> getPost(@PathVariable long id) {
		return ResponseEntity.ok(postService.getPost(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostDTO> updatePost(@PathVariable long id, @Valid @RequestBody PostDTO postDTO) {
		return ResponseEntity.ok(postService.updatePost(id, postDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable long id) {
		postService.deletePost(id);
		return ResponseEntity.ok("Post with id: " + id + " deleted successfully");
	}
}
