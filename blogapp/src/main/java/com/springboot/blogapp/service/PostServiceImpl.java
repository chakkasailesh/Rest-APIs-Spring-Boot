package com.springboot.blogapp.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blogapp.dto.PostDTO;
import com.springboot.blogapp.entity.Post;
import com.springboot.blogapp.exception.ResourceNotFoundException;
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

	@Override
	public List<PostDTO> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Post> postsPage = postRepository.findAll(pageable);
		List<Post> posts = postsPage.getContent();
		return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
	}

	@Override
	public PostDTO getPost(long id) {
		Post post;
		try {
			post = postRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Post", "id", String.valueOf(id));
		}
		return modelMapper.map(post, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(long id, PostDTO postDTO) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
		post.setContent(postDTO.getContent());
		post.setDescription(postDTO.getDescription());
		post.setTitle(postDTO.getTitle());
		return modelMapper.map(postRepository.save(post), PostDTO.class);
	}

	@Override
	public void deletePost(long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
		postRepository.delete(post);
	}

}
