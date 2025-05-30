package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.request.FilterModelDTO;
import com.cibertec.runner.dto.request.ModelDTO;
import com.cibertec.runner.dto.response.ModeloProductoResponse;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Garment;


public interface ModelService {
	
	ResponseEntity<SuccessResponse<List<Garment>>> findAllModel();

	ResponseEntity<SuccessResponse<Garment>> findByIdModel(Integer id);
	
	ResponseEntity<SuccessResponse<Garment>> saveModel(ModelDTO modeloDTO);
	
	ResponseEntity<SuccessResponse<Garment>> updateModel(ModelDTO modeloDTO, Integer id);
	
	ResponseEntity<SuccessResponse<String>> deleteByIdModel(Integer id);

	ResponseEntity<SuccessResponse<List<Garment>>> findByIdMrc(Integer id);
	
	ResponseEntity<SuccessResponse<List<Garment>>> findByAttributes(FilterModelDTO filtro);
	
	ResponseEntity<SuccessResponse<ModeloProductoResponse>> findProductosByModel(Integer id);
}
