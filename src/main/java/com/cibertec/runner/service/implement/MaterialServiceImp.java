package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Material;
import com.cibertec.runner.repository.IMaterialRepository;
import com.cibertec.runner.service.MaterialService;

import jakarta.persistence.NoResultException;

@Service
public class MaterialServiceImp implements MaterialService{

    @Autowired
    private IMaterialRepository repository;

    @Override
    public ResponseEntity<SuccessResponse<List<Material>>> findAllMateriales() {
        List<Material> materiales = repository.findAll();

        if (materiales.isEmpty()) {
        	throw new NoResultException("No se encontro ningun material");
        }
        
        SuccessResponse<List<Material>> success = SuccessResponse.<List<Material>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(materiales)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Material>> findByIdMateriales(Integer id) {
        Material material = repository.findById(id).orElse(null);

        if (material == null) {
        	throw new NoResultException("No se encontro el codigo de material");
        }
        
        SuccessResponse<Material> success = SuccessResponse.<Material>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(material)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Material>> saveMaterial(Material m) {
		if(repository.existsByNombre(m.getName())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
    	Material mat = new Material();
    	mat.setName(m.getName());
        Material nuevoMaterial = repository.save(mat);

        SuccessResponse<Material> success = SuccessResponse.<Material>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(nuevoMaterial)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Material>> updateMaterial(Material m, Integer id) {
        Material existente = repository.findById(id).orElse(null);

        if (existente == null) {
        	throw new NoResultException("No se encontro el codigo de material");
        }

        existente.setName(m.getName());
        Material actualizado = repository.save(existente);

        SuccessResponse<Material> success = SuccessResponse.<Material>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(actualizado)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteByIdMaterial(Integer id) {
        Material material = repository.findById(id).orElse(null);

        if (material == null) {
        	throw new NoResultException("No se encontro el codigo de material");
        }

        repository.delete(material);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Material eliminado correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}
