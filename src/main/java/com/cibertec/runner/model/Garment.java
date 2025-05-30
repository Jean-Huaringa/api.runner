package com.cibertec.runner.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_garment")
public class Garment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_grm", nullable = false)
	private Integer id;

	@Column(name = "stock", nullable = false)
	private Integer stock;

	@Column(name = "state", nullable = false)
	private Boolean state;

	@Column(name = "price")
	private Double price;
	
	@Column(name = "size")
	private Double size;

	@Column(name = "id_clr", nullable = false)
	private Integer idClr;
	@ManyToOne
	@JoinColumn(name = "id_clr", referencedColumnName = "id_clr", insertable = false, updatable = false)
	private Color color;

	@Column(name = "id_prd", nullable = false)
	private Integer idPrd;
	@ManyToOne
	@JoinColumn(name = "id_prd", referencedColumnName = "id_prd", insertable = false, updatable = false)
	private Product product;

	@Column(name = "id_mtl", nullable = false)
	private Integer idMtl;
	@ManyToOne
	@JoinColumn(name = "id_mtl", referencedColumnName = "id_mtl", insertable = false, updatable = false)
	private Material material;

	@Column(name = "id_ctg", nullable = false)
	private Integer idCtg;
	@ManyToOne
	@JoinColumn(name = "id_ctg", referencedColumnName = "id_ctg", insertable = false, updatable = false)
	private Category category;

	@Column(name = "id_brd", nullable = false)
	private Integer idBrd;
	@ManyToOne
	@JoinColumn(name = "id_brd", referencedColumnName = "id_brd", insertable = false, updatable = false)
	private Brand brand;

	@Column(name = "id_gnr", nullable = false)
	private Integer idGnr;
	@ManyToOne
	@JoinColumn(name = "id_gnr", referencedColumnName = "id_gnr", insertable = false, updatable = false)
	private Gender gender;
}
