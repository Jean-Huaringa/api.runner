package com.cibertec.runner.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "tb_category")
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_ctg") 
	private Integer id;
	@Column (name = "name", nullable = false, length = 100, unique = true)
	private String name;
}


