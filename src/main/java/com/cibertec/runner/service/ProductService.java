package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.request.FilterProductDTO;
import com.cibertec.runner.dto.request.ProductDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Product;

public interface ProductService {

	public ResponseEntity<SuccessResponse<List<Product>>> findAllProductos();

	public ResponseEntity<SuccessResponse<Product>> findByIdProducto(Integer id);

	public ResponseEntity<SuccessResponse<Product>> saveProducto(ProductDTO productoDTO);

	public ResponseEntity<SuccessResponse<Product>> updateProducto(Product product, Integer id);

	public ResponseEntity<SuccessResponse<String>> deleteByIdProducto(Integer id);

	public ResponseEntity<SuccessResponse<List<Product>>> findByIdMdl(Integer id);
	
	public ResponseEntity<SuccessResponse<List<Product>>> findByAttributes(FilterProductDTO filtro);
}
