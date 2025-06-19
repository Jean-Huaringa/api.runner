package com.cibertec.runner.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_type_product_category")
public class TypeProductCategory {

    @EmbeddedId
    private TypeProductCategoryId id;

    @ManyToOne
	@JoinColumn(name = "id_tpr", referencedColumnName = "id_tpr", insertable = false, updatable = false)
    private TypeProduct typeProduct;

    @ManyToOne
    @JoinColumn(name = "id_ctg", referencedColumnName = "id_ctg", insertable = false, updatable = false)
    private Category category;
}
