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
@Table(name = "tb_model")
public class Model {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mdl", nullable = false)
	private Integer id;
	
	@Column(name = "description", nullable = false, length = 100)
	private String description;
	
	@Column(name = "information", nullable = false, length = 255)
	private String information;
	
	@Column(name = "state", nullable = false)
	private Boolean state;
	
	@Column(name = "price")
	private Double price;

	@Column(name = "id_ctg", nullable = false)
	private Integer idCtg;

	@Column(name = "id_brd", nullable = false)
	private Integer idBrd;

	@Column(name = "id_prn", nullable = false)
	private Integer idPrn;

	@Column(name = "id_mtl", nullable = false)
	private Integer idMtl;
	
    @ManyToOne
	@JoinColumn(name = "id_ctg", referencedColumnName = "id_ctg", insertable = false, updatable = false)
    private Category category;
    
	@ManyToOne
	@JoinColumn(name = "id_mrc", referencedColumnName = "id_mrc", insertable = false, updatable = false)
    private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "id_prn", referencedColumnName = "id_prn", insertable = false, updatable = false)
    private Person person;
	
	@ManyToOne
	@JoinColumn(name = "id_mtl", referencedColumnName = "id_mtl", insertable = false, updatable = false)
    private Material material;
	
}
