package com.cibertec.runner.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Person;

public interface PersonService {

	public ResponseEntity<SuccessResponse<List<Person>>> findAllPersonas();
	
	public ResponseEntity<SuccessResponse<Person>> findByIdPersona(Integer id);
	
	public ResponseEntity<SuccessResponse<Person>> savePersona(Person person);
	
	public ResponseEntity<SuccessResponse<Person>> updatePersona(Person person, Integer id);
	
	public ResponseEntity<SuccessResponse<String>> deleteByIdPersona(Integer id);
}
