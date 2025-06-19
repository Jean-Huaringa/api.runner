package com.cibertec.runner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Brand;

@Repository
public interface IBrandRepository extends JpaRepository<Brand, Integer> {
	boolean existsByName(String name);
	Optional<Brand> findByName(String name);
}
