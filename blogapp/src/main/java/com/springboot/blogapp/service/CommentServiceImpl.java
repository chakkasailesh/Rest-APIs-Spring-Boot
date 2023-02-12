package com.springboot.blogapp.service;

import java.util.List;
import java.util.stream.Collectors;

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
		Comment comment = modelMapper.map(commentDTO, Comment.class);
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));
		comment.setPost(post);
		return modelMapper.map(commentRepository.save(comment), CommentDTO.class);
	}

	@Override
	public List<CommentDTO> getComments(long postId) {
		List<Comment> comments = commentRepository.findByPostId(postId);
//		List<CommentDTO> commentDTOs = new LinkedList<>();
//		for (Comment comment : comments) {
//			commentDTOs.add(modelMapper.map(comment, CommentDTO.class));
//		}
//		return commentDTOs;
		return comments.stream().map(comment -> modelMapper.map(comment, CommentDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public CommentDTO getComment(long postId, long commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));
		Comment comment = commentRepository.findComment(post, commentId);
		if (comment == null) {
			throw new ResourceNotFoundException("Comment of Post " + postId, "id", String.valueOf(commentId));
		}
		return modelMapper.map(comment, CommentDTO.class);
	}

	@Override
	public CommentDTO updateComment(long postId, long commentId, CommentDTO commentDTO) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));
		Comment comment = commentRepository.findComment(post, commentId);
		if (comment == null) {
			throw new ResourceNotFoundException("Comment of Post " + postId, "id", String.valueOf(commentId));
		}
		comment.setBody(commentDTO.getBody());
		comment.setEmail(commentDTO.getEmail());
		comment.setName(commentDTO.getName());
		return modelMapper.map(commentRepository.save(comment), CommentDTO.class);
	}

	@Override
	public void deleteComment(long postId, long commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));
		Comment comment = commentRepository.findComment(post, commentId);
		if (comment == null) {
			throw new ResourceNotFoundException("Comment of Post " + postId, "id", String.valueOf(commentId));
		}
		commentRepository.delete(comment);
	}

}
