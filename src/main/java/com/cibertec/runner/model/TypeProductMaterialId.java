package com.cibertec.runner.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class TypeProductMaterialId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "id_tpr", nullable = false)
    private Integer idTypeProduct;
	@Column(name = "id_mtl", nullable = false)
    private Integer idMaterial;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeProductMaterialId)) return false;
        TypeProductMaterialId that = (TypeProductMaterialId) o;
        return idTypeProduct.equals(that.idTypeProduct) && idMaterial.equals(that.idMaterial);
    }

    @Override
    public int hashCode() {
        return idTypeProduct.hashCode() + idMaterial.hashCode();
    }
}
