package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Material;

public interface MaterialService {

	ResponseEntity<SuccessResponse<List<Material>>> findAllMaterial();
	
	ResponseEntity<SuccessResponse<Material>> findByIdMaterial(Integer id);
	
	ResponseEntity<SuccessResponse<Material>> saveMaterial(Material material);
	
	ResponseEntity<SuccessResponse<Material>> updateMaterial(Material material, Integer id);
	
	ResponseEntity<SuccessResponse<String>> deleteByIdMaterial(Integer id);
}
