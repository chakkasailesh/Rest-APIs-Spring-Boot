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
import com.springboot.blogapp.entity.Category;
import com.springboot.blogapp.entity.Post;
import com.springboot.blogapp.exception.ResourceNotFoundException;
import com.springboot.blogapp.repository.CategoryRepository;
import com.springboot.blogapp.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDTO createPost(PostDTO postDTO) {
		long categoryId = postDTO.getCategoryId();
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", String.valueOf(categoryId)));
		Post post = modelMapper.map(postDTO, Post.class);
		post.setCategory(category);
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
		long categoryId = postDTO.getCategoryId();
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", String.valueOf(categoryId)));
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
		post.setContent(postDTO.getContent());
		post.setDescription(postDTO.getDescription());
		post.setTitle(postDTO.getTitle());
		post.setCategory(category);
		return modelMapper.map(postRepository.save(post), PostDTO.class);
	}

	@Override
	public void deletePost(long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
		postRepository.delete(post);
	}

	@Override
	public List<PostDTO> getPostsByCategory(long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", String.valueOf(categoryId)));
		List<Post> posts = postRepository.findByCategoryId(categoryId);
		return posts.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
	}

}
