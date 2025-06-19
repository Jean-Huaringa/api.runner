package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.dto.response.TypeProductBrandDTO;
import com.cibertec.runner.model.Brand;
import com.cibertec.runner.model.TypeProduct;
import com.cibertec.runner.model.TypeProductBrand;
import com.cibertec.runner.model.TypeProductBrandId;
import com.cibertec.runner.repository.IBrandRepository;
import com.cibertec.runner.repository.ITypeProductBrandRepository;
import com.cibertec.runner.repository.ITypeProductRepository;
import com.cibertec.runner.service.TypeProductBrandService;

import jakarta.persistence.NoResultException;

@Service
public class TypeProductBrandServiceImp implements TypeProductBrandService{

	@Autowired
	private ITypeProductBrandRepository repository;
	@Autowired
	private IBrandRepository repositoryBrand;
	@Autowired
	private ITypeProductRepository repositoryTypeProduct;

	@Override
	public ResponseEntity<SuccessResponse<TypeProductBrand>> findByIdTypeProductBrand(Integer idTpr,  Integer idBrd) {
		TypeProductBrandId id = new TypeProductBrandId();
		id.setIdBrand(idBrd);
		id.setIdTypeProduct(idTpr);
		TypeProductBrand brand = repository.findById(id).orElse(null);

		if (brand == null) {
			throw new NoResultException("No se encontro el codigo de el color");
		}
		
		SuccessResponse<TypeProductBrand> success = SuccessResponse.<TypeProductBrand>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.OK.value())
		        .success(HttpStatus.OK.getReasonPhrase())
		        .response(brand)
		        .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<List<TypeProductBrand>>> findAllTypeProductBrand() {

		List<TypeProductBrand> brand = repository.findAll();

		if (brand.isEmpty()) {
			throw new NoResultException("No se encontro ningun color");
		}
		
		SuccessResponse<List<TypeProductBrand>> success = SuccessResponse.<List<TypeProductBrand>>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.OK.value())
		        .success(HttpStatus.OK.getReasonPhrase())
		        .response(brand)
		        .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<TypeProductBrand>> saveTypeProductBrand(TypeProductBrandDTO typeProductBrand) {
		
		Brand brand = repositoryBrand.findByName(typeProductBrand.getBrand().toLowerCase()).orElse(null);
		
		if(brand == null) {
			Brand obj = new Brand();
			obj.setName(typeProductBrand.getBrand());
			brand = repositoryBrand.save(obj);
		}

		TypeProduct typeProduct = repositoryTypeProduct.findByName(typeProductBrand.getTypeProduct().toLowerCase()).orElse(null);
		
		if(typeProduct == null) {
			TypeProduct obj = new TypeProduct();
			obj.setName(typeProductBrand.getTypeProduct());
			typeProduct = repositoryTypeProduct.save(obj);
		}

		TypeProductBrandId id = new TypeProductBrandId();
		id.setIdBrand(brand.getId());
		id.setIdTypeProduct(typeProduct.getId());
		
		if(repository.existsById(id)) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		TypeProductBrand c = new TypeProductBrand();
		c.setId(id);
		TypeProductBrand col = repository.save(c);

		SuccessResponse<TypeProductBrand> success = SuccessResponse.<TypeProductBrand>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.CREATED.value())
		        .success(HttpStatus.CREATED.getReasonPhrase())
		        .response(col)
		        .build();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(success);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse<String>> deleteTypeProductBrand(Integer idTpr,  Integer idBrd) {
		TypeProductBrandId id = new TypeProductBrandId();
		id.setIdBrand(idBrd);
		id.setIdTypeProduct(idTpr);
		
		TypeProductBrand color= repository.findById(id).orElse(null);

        if (color == null) {
        	throw new NoResultException("No se encontro el codigo de la marca");
        }
        
        repository.deleteByIdBrand(idBrd, idTpr);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Marca eliminada correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
	}

}
