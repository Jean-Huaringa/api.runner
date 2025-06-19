package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.TypeProduct;

public interface TypeProductService {
	ResponseEntity<SuccessResponse<TypeProduct>> findByIdTypeProduct(Integer id);
	
	ResponseEntity<SuccessResponse<List<TypeProduct>>> findAllTypeProduct();
	
	ResponseEntity<SuccessResponse<TypeProduct>> saveTypeProduct(TypeProduct typeProduct);
	
	ResponseEntity<SuccessResponse<String>> deleteTypeProduct(Integer id);
}
