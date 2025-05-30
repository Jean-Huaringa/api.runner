package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Category;
import com.cibertec.runner.repository.ICategoryRepository;
import com.cibertec.runner.service.CategoryService;

import jakarta.persistence.NoResultException;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private ICategoryRepository repository;

	@Override
	public ResponseEntity<SuccessResponse<List<Category>>> findAllListCategoria() {
		
		List<Category> categorias = repository.findAll();

		if (categorias.isEmpty()) {
			throw new RuntimeException("No se encontro ninguna categoria");
		}
		
		SuccessResponse<List<Category>> success = SuccessResponse.<List<Category>>builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.OK.value())
				.success(HttpStatus.OK.getReasonPhrase())
				.response(categorias)
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<Category>> findByIdCategoria(Integer id) {

		Category categoria = repository.findById(id).orElse(null);

		if (categoria == null) {
			throw new NoResultException("No se encontro el codigo de la categoria");
		}

		SuccessResponse<Category> success = SuccessResponse.<Category>builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.OK.value())
				.success(HttpStatus.OK.getReasonPhrase())
				.response(categoria).build();

		return ResponseEntity.status(HttpStatus.OK).body(success);
		
	}

	@Override
	public ResponseEntity<SuccessResponse<Category>> saveCategoria(Category c) {

		if (repository.existsByNombre(c.getName())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		Category categoria = new Category();
		categoria.setName(c.getName());

		Category cate = repository.save(categoria);
		SuccessResponse<Category> success = SuccessResponse.<Category>builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.CREATED.value())
				.success(HttpStatus.CREATED.getReasonPhrase())
				.response(cate)
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(success);

	}

	@Override
	public ResponseEntity<SuccessResponse<Category>> updateCategoria(Category c, Integer id) {

		if (repository.existsByNombre(c.getName())) {
			throw new DataIntegrityViolationException("Error en duplicidad de datos");
		}
		
		Category categoria = repository.findById(id).orElse(null);
		
		if (categoria == null) {
			throw new NoResultException("No se encontro el codigo de la categoria");
		}

		categoria.setName(c.getName());

		Category update = repository.save(categoria);

		SuccessResponse<Category> success = SuccessResponse.<Category>builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.OK.value())
				.success(HttpStatus.OK.getReasonPhrase())
				.response(update)
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

	@Override
	public ResponseEntity<SuccessResponse<String>> deleteByIdCategoria(Integer id) {

		Category buscaCategoria = repository.findById(id).orElse(null);

		if (buscaCategoria == null) {
			throw new NoResultException("No se encontro el codigo de la categoria");
		}
		
		repository.delete(buscaCategoria);

		SuccessResponse<String> success = SuccessResponse.<String>builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.CREATED.value())
				.success(HttpStatus.CREATED.getReasonPhrase())
				.response("Categoria eliminado correctamente")
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(success);
	}

}
