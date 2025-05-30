package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.District;

@Service
public interface DistrictService {

	ResponseEntity<SuccessResponse<District>> findByIdDistrict(Integer id);
	
	ResponseEntity<SuccessResponse<List<District>>> findAllDistrict();
	
	ResponseEntity<SuccessResponse<District>> saveDistrict(District district);
	
	ResponseEntity<SuccessResponse<District>> updateDistrict(District district, Integer id);
	
	ResponseEntity<SuccessResponse<String>> deleteDistrict(Integer id);
}
