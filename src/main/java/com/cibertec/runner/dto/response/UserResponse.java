package com.cibertec.runner.dto.response;

import com.cibertec.runner.model.District;

import lombok.Data;

@Data
public class UserResponse {
	private String nombre;
	private String apellido;
	private String nmrDocumento;
	private String telefono;
	private String correo;
	private District distrito;
}
