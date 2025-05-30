package com.cibertec.runner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Category;
import com.cibertec.runner.service.implement.CategoryServiceImp;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryServiceImp service;

	@GetMapping
	public ResponseEntity<SuccessResponse<List<Category>>> findAllCategory() {
		return service.findAllCategory();
	}

	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Category>> findByIdCategory(@PathVariable Integer id) {
		return service.findByIdCategory(id);
	}

	@PostMapping
	public ResponseEntity<SuccessResponse<Category>> saveCategory(@RequestBody Category category) {
		return service.saveCategory(category);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse<Category>> updateCategory(@RequestBody Category category,
			@PathVariable Integer id) {
		return service.updateCategory(category, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdCategory(@PathVariable Integer id) {
		return service.deleteByIdCategory(id);
	}
}
