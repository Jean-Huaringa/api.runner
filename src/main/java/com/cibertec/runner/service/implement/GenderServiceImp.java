package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Gender;
import com.cibertec.runner.repository.IGenderRepository;
import com.cibertec.runner.service.GenderService;

import jakarta.persistence.NoResultException;

@Service
public class GenderServiceImp implements GenderService{

	@Autowired
	private IGenderRepository repository;

    @Override
    public ResponseEntity<SuccessResponse<List<Gender>>> findAllPerson() {
        List<Gender> personas = repository.findAll();

        if (personas.isEmpty()) {
        	throw new NoResultException("No se encontro ningun tipo de persona");
        }
        
        SuccessResponse<List<Gender>> success = SuccessResponse.<List<Gender>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(personas)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Gender>> findByIdPerson(Integer id) {
    	
    	Gender persona = repository.findById(id).orElse(null);

        if (persona == null) {
        	throw new NoResultException("No se encontro el codigo del tipo de persona");
        }
        
        SuccessResponse<Gender> success = SuccessResponse.<Gender>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(persona)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Gender>> savePerson(Gender gender) {
    	
		if(repository.existsByName(gender.getName())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		Gender newPersona = new Gender();
        newPersona.setName(gender.getName());
        Gender personaGuardada = repository.save(newPersona);

        SuccessResponse<Gender> success = SuccessResponse.<Gender>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(personaGuardada)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }
    
    

    @Override
    public ResponseEntity<SuccessResponse<Gender>> updatePerson(Gender gender, Integer id) {
    	
    	Gender personaExistente = repository.findById(id).orElse(null);

        if (personaExistente == null) {
        	throw new NoResultException("No se encontro el codigo del tipo de persona");
        }

        personaExistente.setName(gender.getName());
        Gender personaActualizada = repository.save(personaExistente);

        SuccessResponse<Gender> success = SuccessResponse.<Gender>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(personaActualizada)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteByIdPerson(Integer id) {
    	
    	Gender persona = repository.findById(id).orElse(null);

        if (persona == null) {
        	throw new NoResultException("No se encontro el codigo del tipo de persona");
        }

        repository.delete(persona);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Persona eliminada con éxito")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
	
}
