package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.dto.response.TypeProductBrandDTO;
import com.cibertec.runner.model.TypeProductBrand;

public interface TypeProductBrandService {

	ResponseEntity<SuccessResponse<List<TypeProductBrand>>> findAllTypeProductBrand();
	
	ResponseEntity<SuccessResponse<TypeProductBrand>> findByIdTypeProductBrand(Integer idTpr,  Integer idBrd);
	
	ResponseEntity<SuccessResponse<TypeProductBrand>> saveTypeProductBrand(TypeProductBrandDTO typeProductBrand);
	
	ResponseEntity<SuccessResponse<String>> deleteTypeProductBrand(Integer idTpr,  Integer idBrd);
}
