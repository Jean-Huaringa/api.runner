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
import com.cibertec.runner.model.Material;
import com.cibertec.runner.service.implement.MaterialServiceImp;

@RestController
@RequestMapping("/api/material")
public class MaterialController {
	
	@Autowired
	private MaterialServiceImp service;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Material>>> findAllMaterial() {
		return service.findAllMaterial();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Material>> findByIdMaterial(@PathVariable Integer id) {
		return service.findByIdMaterial(id);
	}
	
	@PostMapping
	public ResponseEntity<SuccessResponse<Material>> saveMaterial(@RequestBody Material material) {
		return service.saveMaterial(material);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse<Material>> updateMaterial(@RequestBody Material material, @PathVariable Integer id) {
		return service.updateMaterial(material, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdMaterial(@PathVariable Integer id) {
		return service.deleteByIdMaterial(id);
	}
}
