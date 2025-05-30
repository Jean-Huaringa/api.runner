package com.cibertec.runner.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_worker")
public class Worker {
	
	@Id
	@Column(name = "id_wrk", nullable = false)
	private Integer id;
	
	@Column(name = "salary", nullable = false)
	private Double salary;
	
	@Column(name = "state", nullable = false, length = 1)
	private Boolean state;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;
    
    @Column(name = "depurate_date", nullable = true, updatable = true)
    private LocalDateTime depurateDate;
    
    @OneToOne
//    @MapsId
    @JoinColumn(name = "id_wrk", referencedColumnName = "id_usr", insertable = false, updatable = false)
    private User user;
}
