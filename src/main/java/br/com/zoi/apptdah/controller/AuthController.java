package br.com.zoi.apptdah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zoi.apptdah.config.validation.Create;
import br.com.zoi.apptdah.dto.AuthResponseDto;
import br.com.zoi.apptdah.dto.LoginDto;
import br.com.zoi.apptdah.dto.UserDTO;
import br.com.zoi.apptdah.dto.UserRegistrationDTO;
import br.com.zoi.apptdah.service.AuthenticationService;
import br.com.zoi.apptdah.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/createuser")
	public ResponseEntity<UserDTO> create(@Validated(Create.class) @RequestBody UserRegistrationDTO dto) {
		return new ResponseEntity<UserDTO>(usuarioService.createLoginUser(dto), HttpStatus.CREATED);
	}

	@PostMapping("login")
	public ResponseEntity<AuthResponseDto> authenticate(@Valid @RequestBody LoginDto authentication) {

		// 01 - Receive the token from AuthService
		String token = authenticationService.authenticate(authentication);

		// 02 - Set the token as a response using JwtAuthResponse Dto class
		AuthResponseDto authResponseDto = new AuthResponseDto();
		authResponseDto.setAccessToken(token);

		// 03 - Return the response to the user
		return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
	}
	
	
}
