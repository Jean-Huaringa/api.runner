package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Color;

@Repository
public interface IColorRepository extends JpaRepository<Color, Integer> {

	boolean existsByName(String name);
}
