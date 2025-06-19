package com.cibertec.runner.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_type_product_material")
public class TypeProductMaterial {

    @EmbeddedId
    private TypeProductMaterialId id;

    @ManyToOne
	@JoinColumn(name = "id_tpr", referencedColumnName = "id_tpr", insertable = false, updatable = false)
    private TypeProduct typeProduct;

    @ManyToOne
    @JoinColumn(name = "id_mtl", referencedColumnName = "id_mtl", insertable = false, updatable = false)
    private Material material;
}
