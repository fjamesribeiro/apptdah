package br.com.zoi.apptdah.config.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, JwtAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter defaultConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public JwtAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> authorities = new HashSet<>(defaultConverter.convert(jwt));

        // Obtém a role do Supabase do JWT e adiciona como GrantedAuthority
        String role = jwt.getClaim("role");
        if (role != null) {
            authorities.add(() -> "ROLE_" + role.toUpperCase());
        }

        return new JwtAuthenticationToken(jwt, authorities);
    }
}
