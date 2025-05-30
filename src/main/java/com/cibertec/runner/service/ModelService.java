package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.request.FilterModelDTO;
import com.cibertec.runner.dto.request.ModelDTO;
import com.cibertec.runner.dto.response.ModeloProductoResponse;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Model;


public interface ModelService {
	
	public ResponseEntity<SuccessResponse<List<Model>>> findAllModelos();

	public ResponseEntity<SuccessResponse<Model>> findByIdModel(Integer id);
	
	public ResponseEntity<SuccessResponse<Model>> saveModelo(ModelDTO modeloDTO);
	
	public ResponseEntity<SuccessResponse<Model>> updateModelo(ModelDTO modeloDTO, Integer id);
	
	public ResponseEntity<SuccessResponse<String>> deleteByIdModelo(Integer id);

	public ResponseEntity<SuccessResponse<List<Model>>> findByIdMrc(Integer id);
	
	public ResponseEntity<SuccessResponse<List<Model>>> findByAttributes(FilterModelDTO filtro);
	
	public ResponseEntity<SuccessResponse<ModeloProductoResponse>> findProductosByModelo(Integer id);
}
