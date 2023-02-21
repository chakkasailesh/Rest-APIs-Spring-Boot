package com.springboot.blogapp.service;

import com.springboot.blogapp.dto.CategoryDTO;

public interface CategoryService {
	CategoryDTO addCategory(CategoryDTO categoryDTO);

	CategoryDTO getCategory(long categoryId);
}
