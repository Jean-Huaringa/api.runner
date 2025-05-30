package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Material;

public interface MaterialService {

	public ResponseEntity<SuccessResponse<List<Material>>> findAllMateriales();
	
	public ResponseEntity<SuccessResponse<Material>> findByIdMateriales(Integer id);
	
	public ResponseEntity<SuccessResponse<Material>> saveMaterial(Material material);
	
	public ResponseEntity<SuccessResponse<Material>> updateMaterial(Material material, Integer id);
	
	public ResponseEntity<SuccessResponse<String>> deleteByIdMaterial(Integer id);
}
