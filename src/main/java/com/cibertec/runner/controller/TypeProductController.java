package com.cibertec.runner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.TypeProduct;
import com.cibertec.runner.service.implement.TypeProductServiceImp;


@RestController
@RequestMapping("/api/type-product")
public class TypeProductController {

	@Autowired
	private TypeProductServiceImp service;


	@GetMapping("/{id}")
	ResponseEntity<SuccessResponse<TypeProduct>> findByIdTypeProduct(@PathVariable Integer id){
		return service.findByIdTypeProduct(id);
	}
	
	@GetMapping
	ResponseEntity<SuccessResponse<List<TypeProduct>>> findAllTypeProduct(){
		return service.findAllTypeProduct();
	}
	
    @PostMapping
	public ResponseEntity<SuccessResponse<TypeProduct>> saveTypeProductBrand(@RequestBody TypeProduct typeProduct){
		return service.saveTypeProduct(typeProduct);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteTypeProduct(@PathVariable Integer id){
		return service.deleteTypeProduct(id);
	}

}
