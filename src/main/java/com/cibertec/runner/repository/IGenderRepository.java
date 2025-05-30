package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Gender;

@Repository
public interface IGenderRepository extends JpaRepository<Gender, Integer> {
	boolean existsByName(String name);
}
