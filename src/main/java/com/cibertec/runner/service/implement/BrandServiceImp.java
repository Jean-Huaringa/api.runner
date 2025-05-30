package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Brand;
import com.cibertec.runner.repository.IBrandRepository;
import com.cibertec.runner.service.BrandService;

import jakarta.persistence.NoResultException;

@Service
public class BrandServiceImp implements BrandService {

    @Autowired
    private IBrandRepository repository;

    @Override
    public ResponseEntity<SuccessResponse<List<Brand>>> findAllListMarcas() {

        List<Brand> marcas = repository.findAll();

        if (marcas.isEmpty()) {
        	throw new NoResultException("No se encontro ninguna marca");
        }
        
        SuccessResponse<List<Brand>> success = SuccessResponse.<List<Brand>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(marcas)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Brand>> findByIdMarca(Integer id) {

    	Brand marca = repository.findById(id).orElse(null);

        if (marca == null) {
        	throw new NoResultException("No se encontro el codigo de la marca");
        }
        
        SuccessResponse<Brand> success = SuccessResponse.<Brand>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(marca)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Brand>> saveMarca(Brand marca) {
    	
		if(repository.existsByNombre(marca.getName())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		Brand mar = new Brand();
		mar.setName(marca.getName());
		Brand nuevaMarca = repository.save(mar);

        SuccessResponse<Brand> success = SuccessResponse.<Brand>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(nuevaMarca)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Brand>> updateMarca(Brand marca, Integer id) {

		if(repository.existsByNombre(marca.getName())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		Brand existente = repository.findById(id).orElse(null);

        if (existente == null) {
        	throw new NoResultException("No se encontro el codigo de la marca");
        }

        existente.setName(marca.getName());

        Brand actualizada = repository.save(existente);

        SuccessResponse<Brand> success = SuccessResponse.<Brand>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(actualizada)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteByIdMarca(Integer id) {

    	Brand marca = repository.findById(id).orElse(null);

        if (marca == null) {
        	throw new NoResultException("No se encontro el codigo de la marca");
        }
        
        repository.delete(marca);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Marca eliminada correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}
