package br.com.fiap.fase1tc.restaurantes_backend.services;

import br.com.fiap.fase1tc.restaurantes_backend.dtos.UsuarioPasswordRequestDTO;
import br.com.fiap.fase1tc.restaurantes_backend.dtos.UsuarioRequestDTO;
import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;
import br.com.fiap.fase1tc.restaurantes_backend.factories.UsuarioFactory;
import br.com.fiap.fase1tc.restaurantes_backend.repositories.UsuarioRepository;
import br.com.fiap.fase1tc.restaurantes_backend.services.exceptions.EmailJaCadastradoException;
import br.com.fiap.fase1tc.restaurantes_backend.services.exceptions.FalhaEmManipularUsuarioException;
import br.com.fiap.fase1tc.restaurantes_backend.services.exceptions.LoginJaCadastradoException;
import br.com.fiap.fase1tc.restaurantes_backend.services.exceptions.SenhaEConfirmacaoDiferentesException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> findAllByNome(String nome) {
        return this.usuarioRepository.findAllByNome(nome);
    }

    public void save(UsuarioRequestDTO usuarioDTO) {
        if (!usuarioDTO.senha().equals(usuarioDTO.confirmacaoSenha())) {
            throw new SenhaEConfirmacaoDiferentesException();
        }
        if (this.usuarioRepository.findByEmail(usuarioDTO.email()).isPresent()) {
            throw new EmailJaCadastradoException(usuarioDTO.email());
        }
        if (this.usuarioRepository.findByLogin(usuarioDTO.login()).isPresent()) {
            throw new LoginJaCadastradoException(usuarioDTO.login());
        }
        Usuario usuario = UsuarioFactory.createUsuario(usuarioDTO.perfil());
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setLogin(usuarioDTO.login());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.senha()));
        usuario.setLogradouro(usuarioDTO.logradouro());
        usuario.setNumero(usuarioDTO.numero());
        usuario.setComplemento(usuarioDTO.complemento());
        usuario.setBairro(usuarioDTO.bairro());
        usuario.setCidade(usuarioDTO.cidade());
        usuario.setEstado(usuarioDTO.estado());
        usuario.setCep(usuarioDTO.cep());
        var save = this.usuarioRepository.save(usuario);
        if (save == 0) {
            throw new FalhaEmManipularUsuarioException("Erro ao salvar usuário " +  usuario.getNome() + ".");
        }
    }

    public void updatePassword(UsuarioPasswordRequestDTO usuarioPasswordDTO, Long id) {
        if (usuarioPasswordDTO.senha() == null ||
            usuarioPasswordDTO.confirmacaoSenha() == null) {
            throw new FalhaEmManipularUsuarioException("Dados informados inconsistentes.");
        }
        if (!usuarioPasswordDTO.senha().equals(usuarioPasswordDTO.confirmacaoSenha())) {
            throw new SenhaEConfirmacaoDiferentesException();
        }
        String senhaCriptografada = passwordEncoder.encode(usuarioPasswordDTO.senha());
        var updatePassword = this.usuarioRepository.updatePassword(senhaCriptografada, id);
        if (updatePassword == 0) {
            throw new FalhaEmManipularUsuarioException("Falha ao atualizar a senha do usuário id " + id + ".");
        }
    }

    public void update(UsuarioRequestDTO usuarioDTO, Long id) {
        Optional<Usuario> usuarioOptionalCheck = this.usuarioRepository.findByEmail(usuarioDTO.email());
        if (usuarioOptionalCheck.isPresent() &&
                !usuarioOptionalCheck.get().getId().equals(id)) {
            throw new EmailJaCadastradoException(usuarioDTO.email());
        }
        usuarioOptionalCheck = this.usuarioRepository.findByLogin(usuarioDTO.login());
        if (usuarioOptionalCheck.isPresent() &&
                !usuarioOptionalCheck.get().getId().equals(id)) {
            throw new LoginJaCadastradoException(usuarioDTO.login());
        }
        Usuario usuario = UsuarioFactory.createUsuario(usuarioDTO.perfil());
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setLogin(usuarioDTO.login());
        usuario.setLogradouro(usuarioDTO.logradouro());
        usuario.setNumero(usuarioDTO.numero());
        usuario.setComplemento(usuarioDTO.complemento());
        usuario.setBairro(usuarioDTO.bairro());
        usuario.setCidade(usuarioDTO.cidade());
        usuario.setEstado(usuarioDTO.estado());
        usuario.setCep(usuarioDTO.cep());
        var update = this.usuarioRepository.update(usuario, id);
        if (update == 0) {
            throw new FalhaEmManipularUsuarioException("Falha em atualizar o usuário id " + id + ".");
        }
    }

    public void delete(Long id) {
        var delete = this.usuarioRepository.delete(id);
        if (delete == 0) {
            throw new FalhaEmManipularUsuarioException("Falha em excluir o usuário id " + id + ".");
        }
    }

}
