package com.cibertec.runner.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class TransactionId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_prd", nullable = false)
	private Integer idPrd;
	
	@Column(name = "id_tck", nullable = false)
	private Integer idTck;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
        	return true;
        }
        if (o == null || getClass() != o.getClass()) {
        	return false;
        }
        TransactionId idTrs = (TransactionId) o;
        return Objects.equals(idTck, idTrs.idTck) && Objects.equals(idPrd, idTrs.idPrd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPrd, idTck);
    }
}
