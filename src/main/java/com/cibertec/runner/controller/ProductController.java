package com.cibertec.runner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.request.FilterProductDTO;
import com.cibertec.runner.dto.request.ProductDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Product;
import com.cibertec.runner.service.implement.ProductServiceImp;


@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductServiceImp service;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Product>>> findAllProduct(){
		return service.findAllProduct();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Product>> findByIdProduct(@PathVariable Integer id) {
	    return service.findByIdProduct(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse<Product>> updateProduct(@RequestBody Product product, @PathVariable Integer id){
		return service.updateProduct(product, id);
	}
	
	@PostMapping
	public ResponseEntity<SuccessResponse<Product>> saveProduct(@RequestBody ProductDTO productDTO){
		return service.saveProduct(productDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdProduct(@PathVariable Integer id) {
	    return service.deleteByIdProduct(id);
	}
	
	@GetMapping("/modelo/{idMdl}")
	public ResponseEntity<SuccessResponse<List<Product>>> findByIdMdl(@PathVariable Integer idMdl) {
	    return service.findByIdMdl(idMdl);
	}
	
	@PostMapping("/filtros")
	public ResponseEntity<SuccessResponse<List<Product>>> findByAttributes(@RequestBody FilterProductDTO filtro) {
	    return service.findByAttributes(filtro);
	}

}
