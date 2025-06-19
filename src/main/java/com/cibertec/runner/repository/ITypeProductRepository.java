package com.cibertec.runner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.TypeProduct;

@Repository
public interface ITypeProductRepository extends JpaRepository<TypeProduct, Integer> {
	
	boolean existsByName(String name);
	
	Optional<TypeProduct> findByName(String name);
	
	@Procedure(procedureName = "delete_type_product")
	void deleteByIdTypeProduct(
	    @Param("p_id_tpr") int idTpr
	);
}
