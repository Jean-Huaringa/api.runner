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
import com.cibertec.runner.model.Model;
import com.cibertec.runner.service.implement.ModelServiceImp;


@RestController
@RequestMapping("/api/modelo")
public class ModelController {
	
	@Autowired
	private ModelServiceImp modeloService;
	
	@GetMapping
	public ResponseEntity<SuccessResponse<List<Model>>> findAllModelos(){
		return modeloService.findAllModelos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse<Model>> findByIdModelos(@PathVariable Integer id){
		return modeloService.findByIdModel(id);
	}
	
	@PostMapping
    public ResponseEntity<SuccessResponse<Model>> saveModelo(@RequestBody ModelDTO modeloDTO) {
        return modeloService.saveModelo(modeloDTO);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<Model>> updateModelo(@RequestBody ModelDTO modeloDTO, @PathVariable Integer id) {
        return modeloService.updateModelo(modeloDTO, id);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse<String>> deleteByIdModelo(@PathVariable Integer id) {
	    return modeloService.deleteByIdModelo(id);
	}
	
	@GetMapping("/marca/{idMrc}")
	public ResponseEntity<SuccessResponse<List<Model>>> findByIdMrc(@PathVariable Integer idMrc) {
	    return modeloService.findByIdMrc(idMrc);
	}
	
	@PostMapping("/filtros")
	public ResponseEntity<SuccessResponse<List<Model>>> findByAttributes(@RequestBody FilterModelDTO filtro) {
	    return modeloService.findByAttributes(filtro);
	}
	
	@GetMapping("/productos-modelo/{id}")
	public ResponseEntity<SuccessResponse<ModeloProductoResponse>> findProductosByModelo(@PathVariable Integer id) {
	    return modeloService.findProductosByModelo(id);
	}
}