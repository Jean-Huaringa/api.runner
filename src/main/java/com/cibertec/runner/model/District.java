package com.cibertec.runner.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_district")
public class District {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_dtc")
	private Integer idDto;
	@Column (name = "name", nullable = false, length = 100, unique = true)
	private String name;
	
}
