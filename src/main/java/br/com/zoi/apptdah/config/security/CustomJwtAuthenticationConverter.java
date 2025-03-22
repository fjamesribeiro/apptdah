package br.com.zoi.apptdah.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import br.com.zoi.apptdah.dto.UserPrincipal;

@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

	@Override
	public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
		String userId = jwt.getClaim("sub"); // ID do usuário no Supabase
		String email = jwt.getClaim("email");
		String role = jwt.getClaim("role"); // A role agora é extraída do token

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		
		// Se a role não estiver definida no primeiro login, assume "BASIC"
		if (role != null && !role.isBlank()) {
			authorities.add(new SimpleGrantedAuthority(role.toUpperCase()));
		} else {
			authorities.add(new SimpleGrantedAuthority("BASIC"));
		}

		return new UsernamePasswordAuthenticationToken(new UserPrincipal(UUID.fromString(userId), email, role), jwt,
				authorities);
	}
}
