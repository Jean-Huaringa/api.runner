package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {
	boolean existsByNombre(String nombre);
}
