package br.com.fiap.fase1tc.restaurantes_backend.repositories;

import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository implements IAuthRepository {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthRepository(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean loginAuth(String login, String senha) {
        Usuario usuario = this.usuarioRepository.findByLogin(login).orElse(null);
        if  (usuario == null) {
            return false;
        }
        return passwordEncoder.matches(senha, usuario.getSenha());
    }

}
