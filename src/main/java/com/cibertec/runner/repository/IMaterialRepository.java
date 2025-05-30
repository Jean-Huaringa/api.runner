package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Material;

@Repository
public interface IMaterialRepository extends JpaRepository<Material, Integer> {
	boolean existsByName(String name);
}
