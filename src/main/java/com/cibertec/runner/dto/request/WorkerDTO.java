package com.cibertec.runner.dto.request;

import com.cibertec.runner.model.User;

import lombok.Data;

@Data
public class WorkerDTO {
	
	private Double salary;
	
	private int worikinHours;
  
    private User user;
}
