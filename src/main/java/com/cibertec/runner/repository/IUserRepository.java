package com.cibertec.runner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.runner.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByMail(String mail);
}
