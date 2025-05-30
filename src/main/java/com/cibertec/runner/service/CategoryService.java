package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Category;

public interface CategoryService {

	public ResponseEntity<SuccessResponse<List<Category>>> findAllListCategoria();
	
	public ResponseEntity<SuccessResponse<Category>> findByIdCategoria(Integer id);
	
	public ResponseEntity<SuccessResponse<Category>> saveCategoria(Category category);
	
	public ResponseEntity<SuccessResponse<Category>> updateCategoria(Category category, Integer id);
	
	public ResponseEntity<SuccessResponse<String>> deleteByIdCategoria(Integer id);
	
}
