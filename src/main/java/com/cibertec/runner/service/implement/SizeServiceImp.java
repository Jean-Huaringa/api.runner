package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Size;
import com.cibertec.runner.repository.ISizeRepository;
import com.cibertec.runner.service.SizeService;

import jakarta.persistence.NoResultException;


@Service
public class SizeServiceImp implements SizeService{
	
	@Autowired
	private ISizeRepository repository;

    @Override
    public ResponseEntity<SuccessResponse<List<Size>>> findAllTalla() {
        List<Size> tallas = repository.findAll(Sort.by("id").ascending());

        if (tallas.isEmpty()) {
        	throw new NoResultException("No se encontro ninguna talla");
        }
        
        SuccessResponse<List<Size>> success = SuccessResponse.<List<Size>>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(tallas)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Size>> findByIdTalla(Integer id) {
    	Size talla = repository.findById(id).orElse(null);

        if (talla == null) {
        	throw new NoResultException("No se encontro el codigo de la talla");
        }
        
        SuccessResponse<Size> success = SuccessResponse.<Size>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(talla)
                .build();

        return ResponseEntity.ok(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Size>> saveTalla(Size talla) {
    	
    	if(repository.existsByNombre(talla.getName())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
        

    	Size newTalla = new Size();
        newTalla.setName(talla.getName());
        Size tallaGuardada = repository.save(newTalla);

        SuccessResponse<Size> success = SuccessResponse.<Size>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(tallaGuardada)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

	@Override
	public ResponseEntity<SuccessResponse<Size>> updateTallla(Size talla, Integer id) {
		if(repository.existsByNombre(talla.getName())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		Size existente = repository.findById(id).orElse(null);

        if (existente == null) {
        	throw new NoResultException("No se encontro el codigo de material");
        }

        existente.setName(talla.getName());
        
        Size actualizado = repository.save(existente);

        SuccessResponse<Size> success = SuccessResponse.<Size>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response(actualizado)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<String>> deleteTalla(Integer id) {
		Size talla = repository.findById(id).orElse(null);

        if (talla == null) {
        	throw new NoResultException("No se encontro el codigo de el distrito");
        }
        
        repository.delete(talla);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Distrito eliminado correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
	}
}
