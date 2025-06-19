package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.TypeProduct;
import com.cibertec.runner.repository.ITypeProductRepository;
import com.cibertec.runner.service.TypeProductService;

import jakarta.persistence.NoResultException;

@Service
public class TypeProductServiceImp implements TypeProductService{

	@Autowired
	private ITypeProductRepository repository;
	
	@Override
	public ResponseEntity<SuccessResponse<TypeProduct>> findByIdTypeProduct(Integer id) {
		TypeProduct brand = repository.findById(id).orElse(null);

		if (brand == null) {
			throw new NoResultException("No se encontro el codigo de el color");
		}
		
		SuccessResponse<TypeProduct> success = SuccessResponse.<TypeProduct>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.OK.value())
		        .success(HttpStatus.OK.getReasonPhrase())
		        .response(brand)
		        .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<List<TypeProduct>>> findAllTypeProduct() {
		List<TypeProduct> category = repository.findAll();

		if (category.isEmpty()) {
			throw new NoResultException("No se encontro ningun color");
		}
		
		SuccessResponse<List<TypeProduct>> success = SuccessResponse.<List<TypeProduct>>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.OK.value())
		        .success(HttpStatus.OK.getReasonPhrase())
		        .response(category)
		        .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<TypeProduct>> saveTypeProduct(TypeProduct typeProduct) {
		
		TypeProduct col = repository.save(typeProduct);

		SuccessResponse<TypeProduct> success = SuccessResponse.<TypeProduct>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.CREATED.value())
		        .success(HttpStatus.CREATED.getReasonPhrase())
		        .response(col)
		        .build();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(success);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse<String>> deleteTypeProduct(Integer id) {
		
		TypeProduct color = repository.findById(id).orElse(null);

        if (color == null) {
        	throw new NoResultException("No se encontro el codigo de la marca");
        }
        
        repository.deleteByIdTypeProduct(id);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Marca eliminada correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
	}

}
