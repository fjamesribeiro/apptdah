package br.com.zoi.apptdah.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.zoi.apptdah.dto.UsuarioDto;
import br.com.zoi.apptdah.model.Role;
import br.com.zoi.apptdah.model.Usuario;
import br.com.zoi.apptdah.repository.RoleRepository;
import br.com.zoi.apptdah.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public UsuarioDto completarCadastro(UUID userId, UsuarioDto dto) {
        Usuario usuario = usuarioRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        modelMapper.map(dto, usuario);

        Role role = roleRepository.findByNome(dto.role().getNome())
            .orElseThrow(() -> new RuntimeException("Role não encontrada"));

        usuario.setRole(role);
        usuario.setUserId(userId);
        usuario.setConfirmado(true);

        usuarioRepository.save(usuario);

        return modelMapper.map(usuario, UsuarioDto.class);
    }

    public UsuarioDto buscarUsuario(UUID userId) {
        Usuario usuario = usuarioRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return modelMapper.map(usuario, UsuarioDto.class);
    }
}