package br.com.zoi.apptdah.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.zoi.apptdah.dto.UsuarioDto;
import br.com.zoi.apptdah.service.UsuarioService;

@RestController()
@RequestMapping("api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/completar-cadastro")
	public ResponseEntity<UsuarioDto> completarCadastro(@RequestParam UUID userId, @RequestBody UsuarioDto dto) {
		return ResponseEntity.ok(usuarioService.completarCadastro(userId, dto));
	}

	@GetMapping("/me")
	public ResponseEntity<UsuarioDto> buscarUsuario(@RequestParam UUID userId) {
		return ResponseEntity.ok(usuarioService.buscarUsuario(userId));
	}
}