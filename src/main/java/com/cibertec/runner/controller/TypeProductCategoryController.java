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
import com.cibertec.runner.dto.response.TypeProductCategoryDTO;
import com.cibertec.runner.model.TypeProductCategory;
import com.cibertec.runner.service.implement.TypeProductCategoryServiceImp;

@RestController
@RequestMapping("/api/type-product-category")
public class TypeProductCategoryController {
	
	@Autowired
	private TypeProductCategoryServiceImp service;

	@GetMapping("/search")
	public ResponseEntity<SuccessResponse<TypeProductCategory>> findByIdTypeProductCategory(@RequestParam Integer idTpr, @RequestParam Integer idCtg){
		return service.findByIdTypeProductCategory(idTpr, idCtg);
	}

	@GetMapping
	public ResponseEntity<SuccessResponse<List<TypeProductCategory>>> findAllTypeProductCategory(){
		return service.findAllTypeProductCategory();
	}

    @PostMapping
	public ResponseEntity<SuccessResponse<TypeProductCategory>> saveTypeProductCategory(@RequestBody TypeProductCategoryDTO typeProductBrand){
		return service.saveTypeProductCategory(typeProductBrand);
	}

	@DeleteMapping
	public ResponseEntity<SuccessResponse<String>> deleteTypeProductCategory(@RequestParam Integer idTpr, @RequestParam Integer idCtg){
		return service.deleteTypeProductCategory(idTpr, idCtg);
	}
}
