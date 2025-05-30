package com.cibertec.runner.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.request.LoginDTO;
import com.cibertec.runner.dto.request.RegisterUserDTO;
import com.cibertec.runner.dto.request.UpdatePasswordDTO;
import com.cibertec.runner.dto.request.UpdateUserDTO;
import com.cibertec.runner.dto.response.UserResponse;
import com.cibertec.runner.model.District;
import com.cibertec.runner.model.User;
import com.cibertec.runner.repository.IDistrictRepository;
import com.cibertec.runner.repository.IUserRepository;
import com.cibertec.runner.service.AccountService;
import com.cibertec.runner.service.JwtService;
import com.cibertec.runner.util.ValidateText;

@Service
public class AccountServiceImp implements AccountService{

	@Autowired
	private ValidateText vt;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private IDistrictRepository distritoRep;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void registerUser(RegisterUserDTO request) {

		if (userRepository.findByMail(request.getCorreo()).isPresent()) {
			throw new IllegalArgumentException("El correo ya está registrado");
		}

		vt.isRequired(request.getNombre(), "Nombre");
		vt.hasOnlyLettersAndSpaces(request.getNombre(), "Nombre");
		vt.hasValidLength(request.getNombre(), 2, 30, "Nombre");

		vt.hasValidLength(request.getApellido(), 2, 30, "Apellido");
		vt.hasOnlyLettersAndSpaces(request.getApellido(), "Apellido");

		vt.hasOnlyNumbers(request.getNmrDocumento(), "Número de documento");
		vt.hasValidLength(request.getNmrDocumento(), 8, 12, "Número de documento");

		vt.hasOnlyNumbers(request.getTelefono(), "Teléfono");
		vt.hasValidLength(request.getTelefono(), 9, 12, "Teléfono");

		vt.isValidGmail(request.getCorreo());

		vt.hasValidLength(request.getContrasenia(), 5, 60, "Contraseña");
		vt.hasNoneCharacterDanger(request.getContrasenia(), "Contraseña");

		User user = new User();
		user.setName(request.getNombre());
		user.setLastname(request.getApellido());
		user.setNmrDocument(request.getNmrDocumento());
		user.setPhone(request.getTelefono());
		user.setMail(request.getCorreo());
		user.setPassword(passwordEncoder.encode(request.getContrasenia()));
		user.setRole("USER");

		District d = distritoRep.findById(request.getIdDto()).orElse(null);
		if (d != null) {
			user.setIdDtc(request.getIdDto());
		} else {
			throw new RuntimeException("No se encontro un distrito");
		}
		userRepository.save(user);
	}

	@Override
	public void updateUser(UpdateUserDTO request) {
		String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userRepository.findByMail(emailAutenticado)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		vt.isRequired(request.getNombre(), "Nombre");
		vt.hasOnlyLettersAndSpaces(request.getNombre(), "Nombre");
		vt.hasValidLength(request.getNombre(), 2, 30, "Nombre");

		vt.isRequired(request.getApellido(), "Apellido");
		vt.hasValidLength(request.getApellido(), 2, 30, "Apellido");
		vt.hasOnlyLettersAndSpaces(request.getApellido(), "Apellido");

		vt.isRequired(request.getNmrDocumento(), "Nmr de documento");
		vt.hasOnlyNumbers(request.getNmrDocumento(), "Nmr de documento");
		vt.hasValidLength(request.getNmrDocumento(), 8, 12, "Nmr de documento");

		vt.isRequired(request.getTelefono(), "Telefono");
		vt.hasOnlyNumbers(request.getTelefono(), "Telefono");
		vt.hasValidLength(request.getTelefono(), 9, 12, "Telefono");

		user.setName(request.getNombre());
		user.setLastname(request.getApellido());
		user.setNmrDocument(request.getNmrDocumento());
		user.setPhone(request.getTelefono());

		userRepository.save(user);

	}

	@Override
	public String signin(LoginDTO request) {
		vt.hasNoneCharacterDanger(request.getCorreo(), "Correo");
		vt.hasNoneCharacterDanger(request.getContrasenia(), "Contraseña");
		User usuario = userRepository.findByMail(request.getCorreo()).orElse(null);

		if (usuario != null && passwordEncoder.matches(request.getContrasenia(), usuario.getPassword())) {
			return jwtService.generateToken(usuario.getMail(), usuario.getRole(), usuario.getName());
		} else {
			throw new BadCredentialsException("Usuario y/o contraseña incorrecta");
		}

	}

	@Override
	public void updatePassword(UpdatePasswordDTO request) {
		String emailAutenticado = SecurityContextHolder.getContext().getAuthentication().getName();

		vt.isRequired(request.getContraseniaActual(), "La contraseña actual");
		vt.hasNoneCharacterDanger(request.getContraseniaActual(), "La contraseña actual");

		vt.isRequired(request.getNuevaContrasenia(), "La nueva contraseña");

		User user = userRepository.findByMail(emailAutenticado)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		if (!passwordEncoder.matches(request.getContraseniaActual(), user.getPassword())) {
			throw new BadCredentialsException("La contraseña actual es incorrecta");
		}

		vt.hasValidLength(request.getNuevaContrasenia(), 5, 60, "Contraseña");
		vt.hasNoneCharacterDanger(request.getNuevaContrasenia(), "Contraseña");

		String newPasswordEncrypted = passwordEncoder.encode(request.getNuevaContrasenia());
		user.setPassword(newPasswordEncrypted);
		userRepository.save(user);
	}

	@Override
	public UserResponse getUsuarioLogueado() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			User user = userRepository.findByMail(authentication.getName())
					.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
			
			UserResponse response = new UserResponse();

			response.setNombre(user.getName());
			response.setApellido(user.getLastname());
			response.setNmrDocumento(user.getNmrDocument());
			response.setTelefono(user.getPhone());
			response.setCorreo(user.getMail());
			response.setDistrito(user.getDistrict());

			return response;

		}

		throw new UsernameNotFoundException("Usuario no autentificado");
	}

}
