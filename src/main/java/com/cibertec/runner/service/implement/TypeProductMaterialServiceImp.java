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
import com.cibertec.runner.dto.response.TypeProductMaterialDTO;
import com.cibertec.runner.model.Material;
import com.cibertec.runner.model.TypeProduct;
import com.cibertec.runner.model.TypeProductMaterial;
import com.cibertec.runner.model.TypeProductMaterialId;
import com.cibertec.runner.repository.IMaterialRepository;
import com.cibertec.runner.repository.ITypeProductMaterialRepository;
import com.cibertec.runner.repository.ITypeProductRepository;
import com.cibertec.runner.service.TypeProductMaterialService;

import jakarta.persistence.NoResultException;

@Service
public class TypeProductMaterialServiceImp implements TypeProductMaterialService{

	@Autowired
	private ITypeProductMaterialRepository repository;
	@Autowired
	private IMaterialRepository repositoryMaterial;
	@Autowired
	private ITypeProductRepository repositoryTypeProduct;

	@Override
	public ResponseEntity<SuccessResponse<TypeProductMaterial>> findByIdTypeProductMaterial(Integer idTpr,  Integer idMtl) {
		TypeProductMaterialId id = new TypeProductMaterialId();
		id.setIdMaterial(idMtl);
		id.setIdTypeProduct(idTpr);
		TypeProductMaterial brand = repository.findById(id).orElse(null);

		if (brand == null) {
			throw new NoResultException("No se encontro el codigo de el color");
		}
		
		SuccessResponse<TypeProductMaterial> success = SuccessResponse.<TypeProductMaterial>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.OK.value())
		        .success(HttpStatus.OK.getReasonPhrase())
		        .response(brand)
		        .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<List<TypeProductMaterial>>> findAllTypeProductMaterial() {

		List<TypeProductMaterial> material = repository.findAll();

		if (material.isEmpty()) {
			throw new NoResultException("No se encontro ningun color");
		}
		
		SuccessResponse<List<TypeProductMaterial>> success = SuccessResponse.<List<TypeProductMaterial>>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.OK.value())
		        .success(HttpStatus.OK.getReasonPhrase())
		        .response(material)
		        .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<TypeProductMaterial>> saveTypeProductMaterial(
			TypeProductMaterialDTO typeProductMaterial) {
		
		Material brand = repositoryMaterial.findByName(typeProductMaterial.getMaterial().toLowerCase()).orElse(null);
		
		if(brand == null) {
			Material obj = new Material();
			obj.setName(typeProductMaterial.getMaterial());
			brand = repositoryMaterial.save(obj);
		}

		TypeProduct typeProduct = repositoryTypeProduct.findByName(typeProductMaterial.getTypeProduct().toLowerCase()).orElse(null);
		
		if(typeProduct == null) {
			TypeProduct obj = new TypeProduct();
			obj.setName(typeProductMaterial.getTypeProduct());
			typeProduct = repositoryTypeProduct.save(obj);
		}

		TypeProductMaterialId id = new TypeProductMaterialId();
		id.setIdMaterial(brand.getId());
		id.setIdTypeProduct(typeProduct.getId());
		
		if(repository.existsById(id)) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		TypeProductMaterial c = new TypeProductMaterial();
		c.setId(id);
		TypeProductMaterial col = repository.save(c);

		SuccessResponse<TypeProductMaterial> success = SuccessResponse.<TypeProductMaterial>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.CREATED.value())
		        .success(HttpStatus.CREATED.getReasonPhrase())
		        .response(col)
		        .build();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(success);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse<String>> deleteTypeProductMaterial(Integer idTpr,  Integer idMtl) {
		TypeProductMaterialId id = new TypeProductMaterialId();
		id.setIdMaterial(idMtl);
		id.setIdTypeProduct(idTpr);
		TypeProductMaterial color= repository.findById(id).orElse(null);

        if (color == null) {
        	throw new NoResultException("No se encontro el codigo de la marca");
        }
        
        repository.deleteByIdMaterial(idTpr, idMtl);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Marca eliminada correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
	}

}
