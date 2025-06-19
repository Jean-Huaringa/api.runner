package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.TypeProductBrand;
import com.cibertec.runner.model.TypeProductBrandId;

@Repository
public interface ITypeProductBrandRepository extends JpaRepository<TypeProductBrand, TypeProductBrandId>{
	
	@Procedure(procedureName = "delete_type_product_brand")
	void deleteByIdBrand(
	    @Param("p_id_brd") int idBrd,
	    @Param("p_id_tpr") int idTpr
	);
}
