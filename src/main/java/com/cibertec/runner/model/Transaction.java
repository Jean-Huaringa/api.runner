package com.cibertec.runner.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_transaction")
public class Transaction {
	
    @EmbeddedId
    private TransactionId id;

	@Column(name = "units", nullable = false)
	private Integer units;
	
	@Column(name = "total_amount", nullable = false, length = 10)
    private Double totalAmount;

	@ManyToOne
	@JoinColumn(name = "id_prd", referencedColumnName = "id_prd", insertable = false, updatable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "id_tck", referencedColumnName = "id_tck", insertable = false, updatable = false)
	private Ticket ticket;
}
