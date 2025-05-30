package com.cibertec.runner.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity

@Table(name = "tb_product", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_mdl", "id_tll", "id_clr" }) })
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prd", nullable = false)
	private Integer id;

	@Column(name = "description", nullable = false, length = 100)
	private String description;

	@Column(name = "information", nullable = false, length = 255)
	private String information;

	@Column(name = "id_tpr", nullable = false)
	private Integer idTpr;
}
