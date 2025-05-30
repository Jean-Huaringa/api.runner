package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Color;

public interface ColorService {
	
	
	ResponseEntity<SuccessResponse<List<Color>>> findAllColor();
	
	ResponseEntity<SuccessResponse<Color>> findByIdColor(Integer id);
	
	ResponseEntity<SuccessResponse<Color>> saveColor(Color color);

	ResponseEntity<SuccessResponse<Color>> updateColor(Color color, Integer id);

	ResponseEntity<SuccessResponse<String>> deleteByIdColor(Integer id);
}
