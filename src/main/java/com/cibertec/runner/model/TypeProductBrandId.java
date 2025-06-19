package com.cibertec.runner.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class TypeProductBrandId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_tpr", nullable = false)
    private Integer idTypeProduct;
	@Column(name = "id_brd", nullable = false)
    private Integer idBrand;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeProductBrandId)) return false;
        TypeProductBrandId that = (TypeProductBrandId) o;
        return idTypeProduct.equals(that.idTypeProduct) && idBrand.equals(that.idBrand);
    }

    @Override
    public int hashCode() {
        return idTypeProduct.hashCode() + idBrand.hashCode();
    }
}
