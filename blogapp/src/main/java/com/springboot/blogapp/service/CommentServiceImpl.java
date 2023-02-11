package com.springboot.blogapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blogapp.dto.CommentDTO;
import com.springboot.blogapp.entity.Comment;
import com.springboot.blogapp.entity.Post;
import com.springboot.blogapp.exception.ResourceNotFoundException;
import com.springboot.blogapp.repository.CommentRepository;
import com.springboot.blogapp.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO createComment(long postId, CommentDTO commentDTO) {
		Comment comment = Comment.createCommentEntity(commentDTO);
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));
		comment.setPost(post);
		return modelMapper.map(commentRepository.save(comment), CommentDTO.class);
	}

}
