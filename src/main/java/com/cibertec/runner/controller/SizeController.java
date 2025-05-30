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
import com.cibertec.runner.model.Size;
import com.cibertec.runner.service.implement.SizeServiceImp;

@RestController
@RequestMapping("/api/size")
public class SizeController {

	@Autowired
	private SizeServiceImp service;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Size>>> findAllSize(){
		return service.findAllSize();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Size>> findByIdSize(@PathVariable Integer id){
		return service.findByIdSize(id);
	}
	
    @PostMapping
    public ResponseEntity<SuccessResponse<Size>> saveSize(@RequestBody Size size) {
        return service.saveSize(size);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<Size>> updateSize(@RequestBody Size size, @PathVariable Integer id) {
        return service.updateSize(size, id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<String>> deleteSize(@PathVariable Integer id) {
        return service.deleteSize(id);
    }
	
}
