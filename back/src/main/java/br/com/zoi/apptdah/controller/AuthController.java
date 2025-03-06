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
public class AuthController {

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
	    AuthResponseDto authResponse = authenticationService.authenticate(authentication);
	    return ResponseEntity.ok(authResponse);
	}
	
	
}
