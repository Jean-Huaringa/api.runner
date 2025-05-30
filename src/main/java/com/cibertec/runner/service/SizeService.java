package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Size;

public interface SizeService {
	
	ResponseEntity<SuccessResponse<List<Size>>> findAllSize();
	
	ResponseEntity<SuccessResponse<Size>> findByIdSize(Integer id);
	
	ResponseEntity<SuccessResponse<Size>> saveSize(Size size);
	
	ResponseEntity<SuccessResponse<Size>> updateSize(Size size, Integer id);
	
	ResponseEntity<SuccessResponse<String>> deleteSize(Integer id);

}
