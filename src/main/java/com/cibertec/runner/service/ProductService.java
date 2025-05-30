package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.request.FilterProductDTO;
import com.cibertec.runner.dto.request.ProductDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Product;

public interface ProductService {

	ResponseEntity<SuccessResponse<List<Product>>> findAllProduct();

	ResponseEntity<SuccessResponse<Product>> findByIdProduct(Integer id);

	ResponseEntity<SuccessResponse<Product>> saveProduct(ProductDTO productoDTO);

	ResponseEntity<SuccessResponse<Product>> updateProduct(Product product, Integer id);

	ResponseEntity<SuccessResponse<String>> deleteByIdProduct(Integer id);

	ResponseEntity<SuccessResponse<List<Product>>> findByIdMdl(Integer id);
	
	ResponseEntity<SuccessResponse<List<Product>>> findByAttributes(FilterProductDTO filtro);
}
