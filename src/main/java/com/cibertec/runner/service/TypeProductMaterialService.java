package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.dto.response.TypeProductMaterialDTO;
import com.cibertec.runner.model.TypeProductMaterial;

public interface TypeProductMaterialService {

	ResponseEntity<SuccessResponse<List<TypeProductMaterial>>> findAllTypeProductMaterial();
	
	ResponseEntity<SuccessResponse<TypeProductMaterial>> findByIdTypeProductMaterial(Integer idTpr,  Integer idMtl);
	
	ResponseEntity<SuccessResponse<TypeProductMaterial>> saveTypeProductMaterial(TypeProductMaterialDTO typeProductMaterial);
	
	ResponseEntity<SuccessResponse<String>> deleteTypeProductMaterial(Integer idTpr,  Integer idMtl);
}
