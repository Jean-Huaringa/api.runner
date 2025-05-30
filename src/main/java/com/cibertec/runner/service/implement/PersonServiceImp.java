package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Person;
import com.cibertec.runner.repository.IPersonRepository;
import com.cibertec.runner.service.PersonService;

import jakarta.persistence.NoResultException;

@Service
public class PersonServiceImp implements PersonService{

	@Autowired
	private IPersonRepository repository;

    @Override
    public ResponseEntity<SuccessResponse<List<Person>>> findAllPersonas() {
        List<Person> personas = repository.findAll();

        if (personas.isEmpty()) {
        	throw new NoResultException("No se encontro ningun tipo de persona");
        }
        
        SuccessResponse<List<Person>> success = SuccessResponse.<List<Person>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(personas)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Person>> findByIdPersona(Integer id) {
    	
    	Person persona = repository.findById(id).orElse(null);

        if (persona == null) {
        	throw new NoResultException("No se encontro el codigo del tipo de persona");
        }
        
        SuccessResponse<Person> success = SuccessResponse.<Person>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(persona)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Person>> savePersona(Person p) {
    	
		if(repository.existsByNombre(p.getName())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		Person newPersona = new Person();
        newPersona.setName(p.getName());
        Person personaGuardada = repository.save(newPersona);

        SuccessResponse<Person> success = SuccessResponse.<Person>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(personaGuardada)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }
    
    

    @Override
    public ResponseEntity<SuccessResponse<Person>> updatePersona(Person p, Integer id) {
    	
    	Person personaExistente = repository.findById(id).orElse(null);

        if (personaExistente == null) {
        	throw new NoResultException("No se encontro el codigo del tipo de persona");
        }

        personaExistente.setName(p.getName());
        Person personaActualizada = repository.save(personaExistente);

        SuccessResponse<Person> success = SuccessResponse.<Person>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(personaActualizada)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteByIdPersona(Integer id) {
    	
    	Person persona = repository.findById(id).orElse(null);

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
