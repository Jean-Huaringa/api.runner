package com.cibertec.runner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.dto.response.TypeProductMaterialDTO;
import com.cibertec.runner.model.TypeProductMaterial;
import com.cibertec.runner.service.implement.TypeProductMaterialServiceImp;

@RestController
@RequestMapping("/api/type-product-material")
public class TypeProductMaterialController {
	
	@Autowired
	private TypeProductMaterialServiceImp service;

	@GetMapping("/search")
	public ResponseEntity<SuccessResponse<TypeProductMaterial>> findByIdTypeProductMaterial(@RequestParam Integer idTpr, @RequestParam Integer idMtl){
		return service.findByIdTypeProductMaterial(idTpr, idMtl);
	}

	@GetMapping
	public ResponseEntity<SuccessResponse<List<TypeProductMaterial>>> findAllTypeProductMaterial(){
		return service.findAllTypeProductMaterial();
	}

    @PostMapping
	public ResponseEntity<SuccessResponse<TypeProductMaterial>> saveTypeProductMaterial(@RequestBody TypeProductMaterialDTO typeProductBrand){
		return service.saveTypeProductMaterial(typeProductBrand);
	}

	@DeleteMapping
	public ResponseEntity<SuccessResponse<String>> deleteTypeProductMaterial(@RequestParam Integer idTpr, @RequestParam Integer idMtl){
		return service.deleteTypeProductMaterial(idTpr, idMtl);
	}

}
