package com.cibertec.runner.dto.request;

import lombok.Data;

@Data
public class ModelDTO {
	private String description;
	private String information;
	private Double price;
	private Integer idCtg;
	private Integer idMrc;
	private Integer idPrn;
	private Integer idMtl;
}
