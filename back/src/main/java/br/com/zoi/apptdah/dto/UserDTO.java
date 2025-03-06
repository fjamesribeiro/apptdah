package br.com.zoi.apptdah.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDTO {
	
	private Long id;

	private String nome;

	private String sobrenome;

	private String email;

//	private String senha;

	private String whatsapp;

	private LocalDate dtnascimento;

	private String sexo;

	private String interesse;

	private String cidade;

	private String estado;

	private String profissao;

	private String escolaridade;

	private String descricao;

	private String lugares;
	

}
