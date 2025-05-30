package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.District;

@Repository
public interface IDistrictRepository extends JpaRepository<District, Integer> {

	boolean existsByNombre(String nombre);
}
