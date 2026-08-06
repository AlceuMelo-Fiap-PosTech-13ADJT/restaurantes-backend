package br.com.fiap.fase1tc.restaurantes_backend.services;

import br.com.fiap.fase1tc.restaurantes_backend.dtos.AuthLoginRequestDTO;
import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;
import br.com.fiap.fase1tc.restaurantes_backend.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Boolean loginAuth(AuthLoginRequestDTO authLoginDTO) {
        Usuario usuario = this.usuarioRepository
                .findByLogin(authLoginDTO.login())
                .orElse(null);
        if  (usuario == null) {
            return false;
        }
        return passwordEncoder.matches(authLoginDTO.senha(), usuario.getSenha());
    }

}
