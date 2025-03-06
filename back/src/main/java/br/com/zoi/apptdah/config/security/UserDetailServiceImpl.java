package br.com.zoi.apptdah.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zoi.apptdah.model.Usuario;
import br.com.zoi.apptdah.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioRepository repository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = repository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado: " + email));


		return User.builder()
				.username(usuario.getEmail())
				.password(usuario.getSenha())
				.authorities(usuario.getRoles()).build();
	}

}
