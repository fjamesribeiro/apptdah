package br.com.zoi.apptdah.config.security;

import java.util.Optional;
import java.util.Set;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import br.com.zoi.apptdah.model.Role;
import br.com.zoi.apptdah.model.RoleName;
import br.com.zoi.apptdah.model.Usuario;
import br.com.zoi.apptdah.repository.RoleRepository;
import br.com.zoi.apptdah.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	private final UsuarioRepository usuarioRepository;
	private final RoleRepository roleRepository;

	public CustomOAuth2UserService(UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
		this.usuarioRepository = usuarioRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	@Transactional
	public OAuth2User loadUser(OAuth2UserRequest userRequest) {
		OAuth2User oAuth2User = super.loadUser(userRequest);

		String email = oAuth2User.getAttribute("email");
		String nome = oAuth2User.getAttribute("given_name");
		String sobrenome = oAuth2User.getAttribute("family_name");
		if (sobrenome == null)
			sobrenome = "Não informado";

		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(email);
		Usuario usuario;

		if (usuarioExistente.isEmpty()) {
			// Criar um novo usuário e salvar no banco
			Role roleUser = roleRepository.findByNome(RoleName.ROLE_USER.toString())
					.orElseThrow(() -> new RuntimeException("Role ROLE_USER não encontrada!"));

			usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setNome(nome);
			usuario.setSobrenome(sobrenome);
			usuario.setSenha(""); // Google não precisa de senha
			usuario.setRoles(Set.of(roleUser));

			usuarioRepository.save(usuario);
		} else {
			usuario = usuarioExistente.get();
		}

		return oAuth2User;
	}
}