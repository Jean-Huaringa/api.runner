package com.cibertec.runner.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
	@Bean // Ejecuta el metodo a penas se ejecuta el programa y guarda el resultado para que lo pueda usar en otros archivos
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
