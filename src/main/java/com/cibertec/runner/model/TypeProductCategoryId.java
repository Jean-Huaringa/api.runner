package com.cibertec.runner.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class TypeProductCategoryId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_tpr", nullable = false)
    private Integer idTypeProduct;
	@Column(name = "id_ctg", nullable = false)
    private Integer idCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeProductCategoryId)) return false;
        TypeProductCategoryId that = (TypeProductCategoryId) o;
        return idTypeProduct.equals(that.idTypeProduct) && idCategory.equals(that.idCategory);
    }

    @Override
    public int hashCode() {
        return idTypeProduct.hashCode() + idCategory.hashCode();
    }
}
