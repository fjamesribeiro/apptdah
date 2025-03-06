package br.com.zoi.apptdah.config.security;

import java.time.Instant;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    // Gera um Access Token com escopos (roles)
    public String generateAccessToken(String email, String roles) {
        Instant now = Instant.now();
        long expiry = 900L; // 15 minutos

        var claims = JwtClaimsSet.builder()
                .issuer("app-tdah")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(email)
                .claim("scope", roles)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    // Gera um Refresh Token sem escopo (apenas com o usuário)
    public String generateRefreshToken(String email) {
        Instant now = Instant.now();
        long expiry = 604800L; // 7 dias

        var claims = JwtClaimsSet.builder()
                .issuer("app-tdah")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(email)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
