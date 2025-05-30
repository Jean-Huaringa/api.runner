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
@RequestMapping("/api/talla")
public class SizeController {

	@Autowired
	private SizeServiceImp talRepo;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Size>>> findAllTalla(){
		return talRepo.findAllTalla();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Size>> findByIdTalla(@PathVariable Integer id){
		return talRepo.findByIdTalla(id);
	}
	
    @PostMapping
    public ResponseEntity<SuccessResponse<Size>> saveTalla(@RequestBody Size talla) {
        return talRepo.saveTalla(talla);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<Size>> updateTallla(@RequestBody Size talla, @PathVariable Integer id) {
        return talRepo.updateTallla(talla, id);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<String>> deleteTalla(@PathVariable Integer id) {
        return talRepo.deleteTalla(id);
    }
	
}
