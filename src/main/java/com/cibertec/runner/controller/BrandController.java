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
import com.cibertec.runner.model.Brand;
import com.cibertec.runner.service.implement.BrandServiceImp;


@RestController
@RequestMapping("/api/brand")
public class BrandController {
	
	@Autowired
	private BrandServiceImp service;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Brand>>> findAllBrand() {
		return service.findAllBrand();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Brand>> findByIdBrand(@PathVariable Integer id) {
		return service.findByIdBrand(id);
	}
	
	@PostMapping
	public ResponseEntity<SuccessResponse<Brand>> saveBrand(@RequestBody Brand brand) {
		return service.saveBrand(brand);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse<Brand>> updateBrand(@RequestBody Brand brand, @PathVariable Integer id) {
		return service.updateBrand(brand, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdBrand(@PathVariable Integer id) {
		return service.deleteByIdBrand(id);
	}
	
}
