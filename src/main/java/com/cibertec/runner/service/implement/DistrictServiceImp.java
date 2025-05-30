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
    public ResponseEntity<SuccessResponse<District>> findByIdDistrict(Integer id) {
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
    public ResponseEntity<SuccessResponse<List<District>>> findAllDistrict() {
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
    public ResponseEntity<SuccessResponse<District>> saveDistrict(District district) {
    	
		if(repository.existsByName(district.getName())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}

		District dis = new District();
		dis.setName(district.getName());
		
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
	public ResponseEntity<SuccessResponse<District>> updateDistrict(District district, Integer id) {

		if(repository.existsByName(district.getName())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		District existente = repository.findById(id).orElse(null);

        if (existente == null) {
        	throw new NoResultException("No se encontro el codigo de material");
        }

        existente.setName(district.getName());
        
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
	public ResponseEntity<SuccessResponse<String>> deleteDistrict(Integer id) {
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
