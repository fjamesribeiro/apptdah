package br.com.zoi.apptdah.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import br.com.zoi.apptdah.config.security.JwtService;
import br.com.zoi.apptdah.dto.AuthResponseDto;
import br.com.zoi.apptdah.dto.LoginDto;
import br.com.zoi.apptdah.model.Usuario;
import br.com.zoi.apptdah.repository.UsuarioRepository;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService, UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
    }

    // 📌 Autenticação via Email/Senha
    public AuthResponseDto authenticate(LoginDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha()));

        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        String accessToken = jwtService.generateAccessToken(dto.getEmail(), roles);
        String refreshToken = jwtService.generateRefreshToken(dto.getEmail());

        return new AuthResponseDto(accessToken, refreshToken);
    }

    // 📌 Autenticação via Google OAuth2
    public AuthResponseDto authenticate(OAuth2User principal) {
        String email = principal.getAttribute("email");

        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(email);
        if (usuarioExistente.isEmpty()) {
            throw new RuntimeException("Usuário do Google não encontrado no banco!");
        }

        String accessToken = jwtService.generateAccessToken(email, "ROLE_USER");
        String refreshToken = jwtService.generateRefreshToken(email);

        return new AuthResponseDto(accessToken, refreshToken);
    }
}
