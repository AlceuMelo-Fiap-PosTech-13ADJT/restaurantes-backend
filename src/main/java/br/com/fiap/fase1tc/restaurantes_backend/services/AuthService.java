package br.com.fiap.fase1tc.restaurantes_backend.services;

import br.com.fiap.fase1tc.restaurantes_backend.dtos.AuthLoginRequestDTO;
import br.com.fiap.fase1tc.restaurantes_backend.dtos.UsuarioResponseFindDTO;
import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;
import br.com.fiap.fase1tc.restaurantes_backend.repositories.UsuarioRepository;
import br.com.fiap.fase1tc.restaurantes_backend.services.exceptions.CredenciaisIncorretasException;
import br.com.fiap.fase1tc.restaurantes_backend.services.exceptions.EntidadeNaoEncontradaException;
import br.com.fiap.fase1tc.restaurantes_backend.services.exceptions.ParametroFaltandoException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseFindDTO loginAuth(AuthLoginRequestDTO authLoginDTO) {
        if (authLoginDTO.login() == null || authLoginDTO.login().equals("")
                || authLoginDTO.senha() == null || authLoginDTO.senha().equals("")) {
            throw new ParametroFaltandoException("Dados de requisição inválidos");
        }
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByLogin(authLoginDTO.login());
        if (usuarioOptional.isEmpty()) {
            throw new CredenciaisIncorretasException("Credenciais incorretas");
        }
        Usuario usuario = usuarioOptional.get();
        if (!passwordEncoder.matches(authLoginDTO.senha(), usuario.getSenha())) {
            throw new CredenciaisIncorretasException("Credenciais incorretas");
        }
        return new UsuarioResponseFindDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getLogradouro(),
                usuario.getNumero(),
                usuario.getComplemento(),
                usuario.getBairro(),
                usuario.getCidade(),
                usuario.getEstado(),
                usuario.getCep(),
                usuario.getPerfil()
        );
    }

}
