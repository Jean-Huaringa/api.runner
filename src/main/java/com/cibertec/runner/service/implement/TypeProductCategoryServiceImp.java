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
import com.cibertec.runner.dto.response.TypeProductCategoryDTO;
import com.cibertec.runner.model.Category;
import com.cibertec.runner.model.TypeProduct;
import com.cibertec.runner.model.TypeProductCategory;
import com.cibertec.runner.model.TypeProductCategoryId;
import com.cibertec.runner.repository.ICategoryRepository;
import com.cibertec.runner.repository.ITypeProductCategoryRepository;
import com.cibertec.runner.repository.ITypeProductRepository;
import com.cibertec.runner.service.TypeProductCategoryService;

import jakarta.persistence.NoResultException;

@Service
public class TypeProductCategoryServiceImp implements TypeProductCategoryService{

	@Autowired
	private ITypeProductCategoryRepository repository;
	@Autowired
	private ICategoryRepository repositoryCategory;
	@Autowired
	private ITypeProductRepository repositoryTypeProduct;

	@Override
	public ResponseEntity<SuccessResponse<TypeProductCategory>> findByIdTypeProductCategory(Integer idTpr,  Integer idCtg) {
		TypeProductCategoryId id = new TypeProductCategoryId();
		id.setIdCategory(idCtg);
		id.setIdTypeProduct(idTpr);
		TypeProductCategory brand = repository.findById(id).orElse(null);

		if (brand == null) {
			throw new NoResultException("No se encontro el codigo de el color");
		}
		
		SuccessResponse<TypeProductCategory> success = SuccessResponse.<TypeProductCategory>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.OK.value())
		        .success(HttpStatus.OK.getReasonPhrase())
		        .response(brand)
		        .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<List<TypeProductCategory>>> findAllTypeProductCategory() {

		List<TypeProductCategory> category = repository.findAll();

		if (category.isEmpty()) {
			throw new NoResultException("No se encontro ningun color");
		}
		
		SuccessResponse<List<TypeProductCategory>> success = SuccessResponse.<List<TypeProductCategory>>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.OK.value())
		        .success(HttpStatus.OK.getReasonPhrase())
		        .response(category)
		        .build();
		
		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<TypeProductCategory>> saveTypeProductCategory(
			TypeProductCategoryDTO typeProductBrand) {
		
		Category brand = repositoryCategory.findByName(typeProductBrand.getCategory().toLowerCase()).orElse(null);
		
		if(brand == null) {
			Category obj = new Category();
			obj.setName(typeProductBrand.getCategory());
			brand = repositoryCategory.save(obj);
		}

		TypeProduct typeProduct = repositoryTypeProduct.findByName(typeProductBrand.getTypeProduct().toLowerCase()).orElse(null);
		
		if(typeProduct == null) {
			TypeProduct obj = new TypeProduct();
			obj.setName(typeProductBrand.getTypeProduct());
			typeProduct = repositoryTypeProduct.save(obj);
		}

		TypeProductCategoryId id = new TypeProductCategoryId();
		id.setIdCategory(brand.getId());
		id.setIdTypeProduct(typeProduct.getId());
		
		if(repository.existsById(id)) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		TypeProductCategory c = new TypeProductCategory();
		c.setId(id);
		TypeProductCategory col = repository.save(c);

		SuccessResponse<TypeProductCategory> success = SuccessResponse.<TypeProductCategory>builder()
		        .timestamp(LocalDateTime.now())
		        .status(HttpStatus.CREATED.value())
		        .success(HttpStatus.CREATED.getReasonPhrase())
		        .response(col)
		        .build();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(success);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse<String>> deleteTypeProductCategory(Integer idTpr,  Integer idCtg) {
		TypeProductCategoryId id = new TypeProductCategoryId();
		id.setIdCategory(idCtg);
		id.setIdTypeProduct(idTpr);
		TypeProductCategory color= repository.findById(id).orElse(null);

        if (color == null) {
        	throw new NoResultException("No se encontro el codigo de la marca");
        }
        
        repository.deleteByIdCategory(idTpr, idCtg);

        SuccessResponse<String> success = SuccessResponse.<String>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK.value())
                .success(HttpStatus.OK.getReasonPhrase())
                .response("Marca eliminada correctamente")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(success);
	}

}
