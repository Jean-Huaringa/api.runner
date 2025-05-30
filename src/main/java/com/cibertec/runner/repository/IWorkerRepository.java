package com.cibertec.runner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.Worker;

@Repository
public interface IWorkerRepository extends JpaRepository<Worker, Integer>{
}
