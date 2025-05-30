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
import com.cibertec.runner.model.District;
import com.cibertec.runner.service.implement.DistrictServiceImp;

@RestController
@RequestMapping("/api/district")
public class DistrictController {
	@Autowired
	private DistrictServiceImp service;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<District>>> findAllDistrict(){
		return service.findAllDistrict();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<District>> findByIdDistrict(@PathVariable Integer id){
		return service.findByIdDistrict(id);
	}
	
    @PostMapping
    public ResponseEntity<SuccessResponse<District>> saveDistrict(@RequestBody District district) {
        return service.saveDistrict(district);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<District>> updateDistrict(@RequestBody District district, @PathVariable Integer id) {
        return service.updateDistrict(district, id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<String>> deleteDistrict(@PathVariable Integer id) {
        return service.deleteDistrict(id);
    }
}
