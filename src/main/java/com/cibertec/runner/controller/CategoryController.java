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
@RequestMapping("/api/categoria")
public class CategoryController {

	@Autowired
	private CategoryServiceImp cs;

	@GetMapping
	public ResponseEntity<SuccessResponse<List<Category>>> findAllListCategoria() {
		return cs.findAllListCategoria();
	}

	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Category>> findByIdCategoria(@PathVariable Integer id) {
		return cs.findByIdCategoria(id);
	}

	@PostMapping
	public ResponseEntity<SuccessResponse<Category>> saveCategoria(@RequestBody Category c) {
		return cs.saveCategoria(c);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse<Category>> updateCategoria(@RequestBody Category c,
			@PathVariable Integer id) {
		return cs.updateCategoria(c, id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdCategoria(@PathVariable Integer id) {
		return cs.deleteByIdCategoria(id);
	}
}
