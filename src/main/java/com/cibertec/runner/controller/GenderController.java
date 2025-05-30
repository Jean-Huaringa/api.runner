package com.cibertec.runner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Gender;
import com.cibertec.runner.service.implement.GenderServiceImp;

@RestController
@RequestMapping("/api/gender")
public class GenderController {
	
	@Autowired
	private GenderServiceImp service;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Gender>>> findAllPerson() {
		return service.findAllPerson();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Gender>> findByIdPerson(@PathVariable Integer id) {
		return service.findByIdPerson(id);
	}
	
	@PostMapping
	public ResponseEntity<SuccessResponse<Gender>> savePerson(@RequestBody Gender gender) {
		return service.savePerson(gender);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse<Gender>> updatePerson(@RequestBody Gender gender, @PathVariable Integer id) {
		return service.updatePerson(gender, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdPerson(@PathVariable Integer id) {
		return service.deleteByIdPerson(id);
	}

}
