package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Person;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Integer> {
	boolean existsByNombre(String nombre);
}
