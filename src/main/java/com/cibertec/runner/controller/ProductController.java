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
@RequestMapping("/api/producto")
public class ProductController {
	
	@Autowired
	private ProductServiceImp psimpl;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Product>>> findAllProductos(){
		return psimpl.findAllProductos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Product>> findByIdProducto(@PathVariable Integer id) {
	    return psimpl.findByIdProducto(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse<Product>> updateProducto(@RequestBody Product producto, @PathVariable Integer id){
		return psimpl.updateProducto(producto, id);
	}
	
	@PostMapping
	public ResponseEntity<SuccessResponse<Product>> saveProducto(@RequestBody ProductDTO productoDTO){
		return psimpl.saveProducto(productoDTO);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdProducto(@PathVariable Integer id) {
	    return psimpl.deleteByIdProducto(id);
	}
	
	@GetMapping("/modelo/{idMdl}")
	public ResponseEntity<SuccessResponse<List<Product>>> findByIdMdl(@PathVariable Integer idMdl) {
	    return psimpl.findByIdMdl(idMdl);
	}
	
	@PostMapping("/filtros")
	public ResponseEntity<SuccessResponse<List<Product>>> findByAttributes(@RequestBody FilterProductDTO filtro) {
	    return psimpl.findByAttributes(filtro);
	}

}
