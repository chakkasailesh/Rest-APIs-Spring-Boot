package com.springboot.blogapp.service;

import java.util.List;

import com.springboot.blogapp.dto.CategoryDTO;

public interface CategoryService {
	CategoryDTO addCategory(CategoryDTO categoryDTO);

	CategoryDTO getCategory(long categoryId);

	List<CategoryDTO> getAllCategories();

	CategoryDTO updateCategory(long categoryId, CategoryDTO categoryDTO);

	String deleteCategory(long categoryId);

//	List<PostDTO> getPostsByCategory(long categoryId);
}
