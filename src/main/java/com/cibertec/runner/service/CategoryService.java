package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Category;

public interface CategoryService {

	ResponseEntity<SuccessResponse<List<Category>>> findAllCategory();
	
	ResponseEntity<SuccessResponse<Category>> findByIdCategory(Integer id);
	
	ResponseEntity<SuccessResponse<Category>> saveCategory(Category category);
	
	ResponseEntity<SuccessResponse<Category>> updateCategory(Category category, Integer id);
	
	ResponseEntity<SuccessResponse<String>> deleteByIdCategory(Integer id);
	
}
