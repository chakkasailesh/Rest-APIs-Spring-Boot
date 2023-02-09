package com.springboot.blogapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blogapp.dto.PostDTO;
import com.springboot.blogapp.entity.Post;
import com.springboot.blogapp.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDTO createPost(PostDTO postDTO) {
		Post post = Post.createPostEntity(postDTO);
		return modelMapper.map(postRepository.save(post), PostDTO.class);
	}

}
