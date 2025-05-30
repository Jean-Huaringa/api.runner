package com.cibertec.runner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Garment;


@Repository
public interface IModelRepository extends JpaRepository<Garment, Integer>{
	
	List<Garment> findAllByState(Boolean state);
	List<Garment> findByIdBrd(Integer idBrd);
	
	@Procedure(procedureName = "filtrar_modelos")
	List<Garment> filtrarModelos(
	    @Param("p_id_clr") String idClr,
	    @Param("p_id_tll") String idTll,
	    @Param("p_id_ctg") String idCtg,
	    @Param("p_id_mrc") String idMrc,
	    @Param("p_id_prn") String idPrn,
	    @Param("p_id_mtl") String idMtl
	);
}
