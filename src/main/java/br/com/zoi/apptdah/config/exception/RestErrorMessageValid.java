package br.com.zoi.apptdah.config.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class RestErrorMessageValid extends RestErrorMessage {

	private List<String> errors; // Adicionando uma lista de detalhes dos erros

	public RestErrorMessageValid(HttpStatus status, String message, List<String> errors) {
		super(status, message);
		this.errors = errors;
	}


}
