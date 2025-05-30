package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Size;

public interface SizeService {
	
	public ResponseEntity<SuccessResponse<List<Size>>> findAllTalla();
	
	public ResponseEntity<SuccessResponse<Size>> findByIdTalla(Integer id);
	
	public ResponseEntity<SuccessResponse<Size>> saveTalla(Size size);
	
	public ResponseEntity<SuccessResponse<Size>> updateTallla(Size size, Integer id);
	
	public ResponseEntity<SuccessResponse<String>> deleteTalla(Integer id);

}
