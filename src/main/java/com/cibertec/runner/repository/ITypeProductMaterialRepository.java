package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.TypeProductMaterial;
import com.cibertec.runner.model.TypeProductMaterialId;

@Repository
public interface ITypeProductMaterialRepository extends JpaRepository<TypeProductMaterial, TypeProductMaterialId>{

	
	@Procedure(procedureName = "delete_type_product_material")
	void deleteByIdMaterial(
	    @Param("p_id_mtl") int idMtl,
	    @Param("p_id_tpr") int idTpr
	);
}
