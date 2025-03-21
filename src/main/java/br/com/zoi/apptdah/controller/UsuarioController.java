package br.com.zoi.apptdah.controller;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zoi.apptdah.model.Role;
import br.com.zoi.apptdah.model.Usuario;
import br.com.zoi.apptdah.repository.RoleRepository;
import br.com.zoi.apptdah.repository.UsuarioRepository;

@RestController()
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/complete-profile")
    public String completarCadastro(@RequestBody Usuario usuario, @AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject()); // ID do usuário no Supabase

        Optional<Usuario> existingProfile = usuarioRepository.findById(userId);
        if (existingProfile.isPresent()) {
            return "Perfil já cadastrado!";
        }

        Role defaultRole = roleRepository.findByNome("USER")
            .orElseThrow(() -> new RuntimeException("Role USER não encontrada!"));

        usuario.setUserId(userId);
        usuario.setRoles(Set.of(defaultRole));

        usuarioRepository.save(usuario);
        return "Perfil criado com sucesso!";
    }
    
    @GetMapping("/me")
    public Map<String, Object> getUserDetails(@AuthenticationPrincipal Jwt jwt) {
        if (jwt == null) {
            return Map.of("error", "Usuário não autenticado");
        }

        // Obtendo os claims do JWT (email, nome, etc.)
        return Map.of(
                "id", jwt.getSubject(), // ID do usuário
                "email", jwt.getClaim("email"),
                "name", jwt.getClaim("name"),
                "roles", jwt.getClaim("role") // Se o token incluir roles
        );
    }
}