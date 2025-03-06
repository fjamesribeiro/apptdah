package br.com.zoi.apptdah.dto;

import java.util.Set;

import br.com.zoi.apptdah.config.validation.Create;
import br.com.zoi.apptdah.config.validation.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDTO {

	@Size(groups = { Create.class, Update.class }, min = 2, message = "O Nome deve ter no mínimo 2 caracteres")
	@NotBlank(groups = Create.class, message = "O nome é obrigatório")
	private String nome;

	@Size(groups = { Create.class, Update.class }, min = 2, message = "O sobrenome deve ter no mínimo 2 caracteres")
	@NotBlank(groups = Create.class, message = "O sobrenome é obrigatório")
	private String sobrenome;

	@NotBlank(groups = Create.class, message = "O email é obrigatório")
	@Pattern(groups = { Create.class,
			Update.class }, regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "E-mail inválido")
	private String email;

	@NotBlank(groups = Create.class, message = "A senha é obrigatório")
	@Size(groups = { Create.class, Update.class }, min = 8, message = "O senha deve ter no mínimo 8 caracteres")	
	private String senha;

	private Set roles;

}
