package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.District;

@Service
public interface DistrictService {

	public ResponseEntity<SuccessResponse<District>> findByIdDistrito(Integer id);
	
	public ResponseEntity<SuccessResponse<List<District>>> findAllDistrito();
	
	public ResponseEntity<SuccessResponse<District>> saveDistrito(District district);
	
	public ResponseEntity<SuccessResponse<District>> updateDistrito(District district, Integer id);
	
	public ResponseEntity<SuccessResponse<String>> deleteDistrito(Integer id);
}
