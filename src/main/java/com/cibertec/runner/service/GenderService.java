package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Gender;

public interface GenderService {

	ResponseEntity<SuccessResponse<List<Gender>>> findAllPerson();
	
	ResponseEntity<SuccessResponse<Gender>> findByIdPerson(Integer id);
	
	ResponseEntity<SuccessResponse<Gender>> savePerson(Gender person);
	
	ResponseEntity<SuccessResponse<Gender>> updatePerson(Gender person, Integer id);
	
	ResponseEntity<SuccessResponse<String>> deleteByIdPerson(Integer id);
}
