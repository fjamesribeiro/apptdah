package br.com.zoi.apptdah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zoi.apptdah.config.validation.Create;
import br.com.zoi.apptdah.dto.UserAdditionalInfoDTO;
import br.com.zoi.apptdah.dto.UserDTO;
import br.com.zoi.apptdah.service.UsuarioService;

@RestController()
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/completecaduser")
	public ResponseEntity<UserDTO> completeCadUser(@Validated(Create.class) @RequestBody UserAdditionalInfoDTO dto) {
		return new ResponseEntity<UserDTO>(usuarioService.completeCadUser(dto), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<UserDTO>> findAll() {
		return new ResponseEntity<List<UserDTO>>(usuarioService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findbyId(@PathVariable Long id) {
		return new ResponseEntity<UserDTO>(usuarioService.findById(id), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<UserDTO> putMethodName(@RequestBody UserDTO dto) {
		return new ResponseEntity<UserDTO>(usuarioService.update(dto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		usuarioService.delete(id);
	}

}
