package com.cibertec.runner.dto.request;

import java.util.List;

import lombok.Data;

@Data
public class FilterProductDTO {
    private List<Integer> idClr;
    private List<Integer> idTll;
    private List<Integer> idCtg;
    private List<Integer> idMrc;
    private List<Integer> idPrn;
    private List<Integer> idMtl;
}
