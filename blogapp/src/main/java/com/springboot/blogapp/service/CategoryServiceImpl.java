package com.springboot.blogapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blogapp.dto.CategoryDTO;
import com.springboot.blogapp.entity.Category;
import com.springboot.blogapp.exception.ResourceNotFoundException;
import com.springboot.blogapp.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO addCategory(CategoryDTO categoryDTO) {
		Category category = modelMapper.map(categoryDTO, Category.class);
		return modelMapper.map(categoryRepository.save(category), CategoryDTO.class);
	}

	@Override
	public CategoryDTO getCategory(long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", String.valueOf(categoryId)));
		return modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO updateCategory(long categoryId, CategoryDTO categoryDTO) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", String.valueOf(categoryId)));
		category.setDescription(categoryDTO.getDescription());
		category.setName(categoryDTO.getName());
		return modelMapper.map(categoryRepository.save(category), categoryDTO.getClass());
	}

	@Override
	public String deleteCategory(long categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", String.valueOf(categoryId)));
		categoryRepository.delete(category);
		return "Category deleted successfully";
	}

//	@Override
//	public List<PostDTO> getPostsByCategory(long categoryId) {
//		Category category = categoryRepository.findById(categoryId)
//				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", String.valueOf(categoryId)));
//		System.out.println(category);
//		return category.getPosts().stream().map(post -> modelMapper.map(post, PostDTO.class)).toList();
//	}
}
