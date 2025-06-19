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
import com.cibertec.runner.dto.response.TypeProductBrandDTO;
import com.cibertec.runner.model.TypeProductBrand;
import com.cibertec.runner.service.implement.TypeProductBrandServiceImp;

@RestController
@RequestMapping("/api/type-product-brand")
public class TypeProductBrandController {
	
	@Autowired
	private TypeProductBrandServiceImp service;


	// @GetMapping(params = {"id1", "id2"})
	@GetMapping("/search")
	public ResponseEntity<SuccessResponse<TypeProductBrand>> findByIdTypeProductBrand(@RequestParam Integer idTpr, @RequestParam Integer idBrd){
		return service.findByIdTypeProductBrand(idTpr, idBrd);
	}

	@GetMapping
	public ResponseEntity<SuccessResponse<List<TypeProductBrand>>> findAllTypeProductBrand(){
		return service.findAllTypeProductBrand();
	}

    @PostMapping
	public ResponseEntity<SuccessResponse<TypeProductBrand>> saveTypeProductBrand(@RequestBody TypeProductBrandDTO typeProductBrand){
		return service.saveTypeProductBrand(typeProductBrand);
	}

	@DeleteMapping
	public ResponseEntity<SuccessResponse<String>> deleteTypeProductBrand(@RequestParam Integer idTpr, @RequestParam Integer idBrd){
		return service.deleteTypeProductBrand(idTpr, idBrd);
	}
}
