package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.District;
import com.cibertec.runner.repository.IDistrictRepository;
import com.cibertec.runner.service.DistrictService;

import jakarta.persistence.NoResultException;

@Service
public class DistrictServiceImp implements DistrictService {

    @Autowired
    private IDistrictRepository repository;

    @Override
    public ResponseEntity<SuccessResponse<District>> findByIdDistrito(Integer id) {
    	District distrito = repository.findById(id).orElse(null);

        if (distrito == null) {
			throw new NoResultException("No se encontro el codigo de la distrito");
        }
            SuccessResponse<District> success = SuccessResponse.<District>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(distrito)
                .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<List<District>>> findAllDistrito() {
        List<District> distritos = repository.findAll();

        if (distritos.isEmpty()) {
			throw new NoResultException("No se encontro ningun distrito");
        }
            SuccessResponse<List<District>> success = SuccessResponse.<List<District>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(distritos)
                .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<District>> saveDistrito(District distrito) {
    	
		if(repository.existsByNombre(distrito.getName())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}

		District dis = new District();
		dis.setName(distrito.getName());
		
		District savedDistrito = repository.save(dis);

        SuccessResponse<District> success = SuccessResponse.<District>builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.CREATED.value())
            .success(HttpStatus.CREATED.getReasonPhrase())
            .response(savedDistrito)
            .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

	@Override
	public ResponseEntity<SuccessResponse<District>> updateDistrito(District distrito, Integer id) {

		if(repository.existsByNombre(distrito.getName())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		District existente = repository.findById(id).orElse(null);

        if (existente == null) {
        	throw new NoResultException("No se encontro el codigo de material");
        }

        existente.setName(distrito.getName());
        
        District actualizado = repository.save(existente);

        SuccessResponse<District> success = SuccessResponse.<District>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(actualizado)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<String>> deleteDistrito(Integer id) {
		District distrito= repository.findById(id).orElse(null);

        if (distrito == null) {
        	throw new NoResultException("No se encontro el codigo de el distrito");
        }
        
        repository.delete(distrito);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Distrito eliminado correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
	}
}
