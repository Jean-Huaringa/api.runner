package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.dto.response.TypeProductCategoryDTO;
import com.cibertec.runner.model.TypeProductCategory;

public interface TypeProductCategoryService {
	
	ResponseEntity<SuccessResponse<List<TypeProductCategory>>> findAllTypeProductCategory();

	ResponseEntity<SuccessResponse<TypeProductCategory>> findByIdTypeProductCategory(Integer idTpr,  Integer idCtg);
	
	ResponseEntity<SuccessResponse<TypeProductCategory>> saveTypeProductCategory(TypeProductCategoryDTO typeProductCategory);
	
	ResponseEntity<SuccessResponse<String>> deleteTypeProductCategory(Integer idTpr,  Integer idCtg);
}
