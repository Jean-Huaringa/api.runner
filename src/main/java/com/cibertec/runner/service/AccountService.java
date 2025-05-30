package com.cibertec.runner.service;

import com.cibertec.runner.dto.request.LoginDTO;
import com.cibertec.runner.dto.request.RegisterUserDTO;
import com.cibertec.runner.dto.request.UpdatePasswordDTO;
import com.cibertec.runner.dto.request.UpdateUserDTO;
import com.cibertec.runner.dto.response.UserResponse;

public interface AccountService {
	
	void registerUser(RegisterUserDTO request);
	
	void updateUser(UpdateUserDTO request);
	
	String signin(LoginDTO request);
	
	void updatePassword(UpdatePasswordDTO request);
	
	UserResponse getUsuarioLogueado(); 

}