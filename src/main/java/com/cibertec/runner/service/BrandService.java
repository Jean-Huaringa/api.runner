package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Brand;

public interface BrandService {

	public ResponseEntity<SuccessResponse<List<Brand>>> findAllListMarcas();
	
	public ResponseEntity<SuccessResponse<Brand>> findByIdMarca(Integer id);
	
	public ResponseEntity<SuccessResponse<Brand>> saveMarca(Brand brand);
	
	public ResponseEntity<SuccessResponse<Brand>> updateMarca(Brand brand, Integer id);
	
	public ResponseEntity<SuccessResponse<String>> deleteByIdMarca(Integer id);
}
