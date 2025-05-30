package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Brand;

public interface BrandService {

	ResponseEntity<SuccessResponse<List<Brand>>> findAllBrand();
	
	ResponseEntity<SuccessResponse<Brand>> findByIdBrand(Integer id);
	
	ResponseEntity<SuccessResponse<Brand>> saveBrand(Brand brand);
	
	ResponseEntity<SuccessResponse<Brand>> updateBrand(Brand brand, Integer id);
	
	ResponseEntity<SuccessResponse<String>> deleteByIdBrand(Integer id);
}
