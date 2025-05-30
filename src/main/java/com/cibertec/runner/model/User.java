package com.cibertec.runner.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "tb_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usr")
	private Integer id;

	@Column(name = "name", nullable = false, length = 30)
	private String name;

	@Column(name = "lastname", nullable = false, length = 30)
	private String lastname;

	@Column(name = "nmr_document", nullable = false, length = 12)
	private String nmrDocument;

	@Column(name = "phone", nullable = false, length = 12)
	private String phone;

	@Column(name = "mail", nullable = false, length = 50)
	private String mail;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "role", nullable = false, length = 10)
	private String role;

    @Column(name = "state", nullable = false)
    private boolean state;
    
    @Column(name = "creation_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "delet_date")
    private LocalDateTime deletDate;

	@Column(name = "id_dtc", nullable = false)
	private Integer idDtc;
	
//	@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "id_dtc", referencedColumnName = "id_dtc", insertable = false, updatable = false)
    private District district;
    
    
}
