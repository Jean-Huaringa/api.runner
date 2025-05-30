package com.cibertec.runner.dto.response;

import java.util.List;

import com.cibertec.runner.model.Brand;
import com.cibertec.runner.model.Category;
import com.cibertec.runner.model.Material;
import com.cibertec.runner.model.Gender;
import com.cibertec.runner.model.Product;

import lombok.Data;

@Data
public class ModeloProductoResponse {
	private Integer id;
	private String descripcion;
	private String info;
	private Boolean estado;
	private Double precio;
	private Integer idCtg;
	private Integer idMrc;
	private Integer idPrn;
	private Integer idMtl;
    private Category categoria;
    private Brand marca;
    private Gender persona;
    private Material material;
    private List<Product> productos;
}
