package br.com.zoi.apptdah.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario{

	@Id
    private UUID userId; // Mesmo ID do Supabase

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "sobrenome", nullable = false)
	private String sobrenome;

	@Column(name = "dtnascimento")
	private LocalDate dtnascimento;
	
	@Column(nullable = false, unique = true)
    private String email;	

	@Column(name = "sexo")
	private String sexo;
	
    @Column(nullable = false)
    private boolean confirmado;	

	@Column(name = "diagnostico")
	private String diagnostico;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

}
