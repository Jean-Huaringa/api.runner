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

import com.cibertec.runner.dto.request.FilterModelDTO;
import com.cibertec.runner.dto.request.ModelDTO;
import com.cibertec.runner.dto.response.ModeloProductoResponse;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Garment;
import com.cibertec.runner.service.implement.ModelServiceImp;


@RestController
@RequestMapping("/api/model")
public class ModelController {
	
	@Autowired
	private ModelServiceImp service;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Garment>>> findAllModel(){
		return service.findAllModel();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Garment>> findByIdModel(@PathVariable Integer id){
		return service.findByIdModel(id);
	}
	
	@PostMapping
    public ResponseEntity<SuccessResponse<Garment>> saveModel(@RequestBody ModelDTO modelDTO) {
        return service.saveModel(modelDTO);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<Garment>> updateModel(@RequestBody ModelDTO modelDTO, @PathVariable Integer id) {
        return service.updateModel(modelDTO, id);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdModel(@PathVariable Integer id) {
	    return service.deleteByIdModel(id);
	}
	
	@GetMapping("/marca/{idMrc}")
	public ResponseEntity<SuccessResponse<List<Garment>>> findByIdMrc(@PathVariable Integer idMrc) {
	    return service.findByIdMrc(idMrc);
	}
	
	@PostMapping("/filtros")
	public ResponseEntity<SuccessResponse<List<Garment>>> findByAttributes(@RequestBody FilterModelDTO filtro) {
	    return service.findByAttributes(filtro);
	}
	
	@GetMapping("/productos-modelo/{id}")
	public ResponseEntity<SuccessResponse<ModeloProductoResponse>> findProductosByModel(@PathVariable Integer id) {
	    return service.findProductosByModel(id);
	}
}