package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.TypeProductCategory;
import com.cibertec.runner.model.TypeProductCategoryId;

@Repository
public interface ITypeProductCategoryRepository extends JpaRepository<TypeProductCategory, TypeProductCategoryId>{
	
	@Procedure(procedureName = "delete_type_product_category")
	void deleteByIdCategory(
	    @Param("p_id_ctg") int idCtg,
	    @Param("p_id_tpr") int idTpr
	);

}
