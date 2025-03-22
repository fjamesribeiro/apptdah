package br.com.zoi.apptdah.dto;

import java.time.LocalDate;
import java.util.UUID;

import br.com.zoi.apptdah.model.Role;

public record UsuarioDto(UUID userId, String nome, String sobrenome, LocalDate dtnascimento, String email, String sexo,
		String diagnostico, Role role) {
}