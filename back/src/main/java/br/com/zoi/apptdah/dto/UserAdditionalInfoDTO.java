package br.com.zoi.apptdah.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zoi.apptdah.config.validation.Create;
import br.com.zoi.apptdah.config.validation.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserAdditionalInfoDTO {

	@NotBlank(groups = Create.class, message = "O email é obrigatório")
	@Pattern(groups = { Create.class,
			Update.class }, regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "E-mail inválido")
	private String email;

	@NotBlank(groups = Create.class, message = "O whatsapp é obrigatório")
	@Pattern(groups = { Create.class,
			Update.class }, regexp = "\\(\\d{2}\\)(9\\d{4}-\\d{4}|99999-9999)", message = "Formato de whatsapp inválido. O formato correto é (XX)XXXXX-XXXX, exceto quando for (XX)99999-9999")
	private String whatsapp;

	@NotNull(groups = Create.class, message = "A data de nascimento é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtnascimento;

	@NotBlank(groups = Create.class, message = "O sexo é obrigatório")
	@Pattern(groups = { Create.class, Update.class }, regexp = "^[mMfFoO]$", message = "O sexo deve ser 'M', 'F' ou 'O'")
	private String sexo;

	@NotBlank(groups = Create.class, message = "O interesse é obrigatório")
	@Pattern(groups = { Create.class,
			Update.class }, regexp = "^[mMfFAa]$", message = "O interesse deve ser 'M', 'F' ou 'A'")
	private String interesse;

	@Size(groups = { Create.class, Update.class }, min = 3, message = "A cidade deve ter no mínimo 3 caracteres")
	private String cidade;

	@Pattern(groups = { Create.class,
			Update.class }, regexp = "^[a-zA-Z]{2}$", message = "O estado deve conter exatamente duas letras")
	private String estado;

	@Size(groups = { Create.class, Update.class }, min = 2, message = "A profissao deve ter no mínimo 2 caracteres")
	private String profissao;

	@Size(groups = { Create.class, Update.class }, min = 2, message = "A escolaridade deve ter no mínimo 2 caracteres")
	private String escolaridade;

	@Size(groups = { Create.class, Update.class }, min = 2, message = "A descricao deve ter no mínimo 2 caracteres")
	private String descricao;

	@Size(groups = { Create.class, Update.class }, min = 2, message = "Os lugares deve ter no mínimo 2 caracteres")
	private String lugares;

}
