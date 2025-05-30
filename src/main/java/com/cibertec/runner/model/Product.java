package com.cibertec.runner.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity

@Table(name = "tb_product", uniqueConstraints = { @UniqueConstraint (columnNames = { "id_mdl", "id_tll", "id_clr" }) })
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prd", nullable = false)
    private Integer id;

	@Column(name = "stock", nullable = false)
	private Integer stock;
	
	@Column(name = "id_clr", nullable = false)
	private Integer idClr;
	
	@Column(name = "id_tll", nullable = false)
	private Integer idTll;
	
	@Column(name = "id_mdl", nullable = false)
	private Integer idMdl;

	@ManyToOne
	@JoinColumn(name = "id_mdl", referencedColumnName = "id_mdl", insertable = false, updatable = false)
	private Model modelo;

	@ManyToOne
	@JoinColumn(name = "id_tll", referencedColumnName = "id_tll", insertable = false, updatable = false)
	private Size talla;

	@ManyToOne
	@JoinColumn(name = "id_clr", referencedColumnName = "id_clr", insertable = false, updatable = false)
	private Color color;

}
