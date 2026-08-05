package br.com.fiap.fase1tc.restaurantes_backend.services;

import br.com.fiap.fase1tc.restaurantes_backend.dtos.UsuarioPasswordRequestDTO;
import br.com.fiap.fase1tc.restaurantes_backend.dtos.UsuarioRequestDTO;
import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;
import br.com.fiap.fase1tc.restaurantes_backend.factories.UsuarioFactory;
import br.com.fiap.fase1tc.restaurantes_backend.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
            throw new RuntimeException("a senha e a confirmação da senha devem ser iguais");
        }
        if (this.usuarioRepository.findByEmail(usuarioDTO.email()).isPresent()) {
            throw new RuntimeException("email já existe");
        }
        if (this.usuarioRepository.findByLogin(usuarioDTO.login()).isPresent()) {
            throw new RuntimeException("login já existe");
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
        Assert.state(save == 1, "Erro ao salvar usuário " +  usuario.getNome());
    }

    public void updatePassword(UsuarioPasswordRequestDTO usuarioPasswordDTO, Long id) {
        if (!usuarioPasswordDTO.senha().equals(usuarioPasswordDTO.confirmacaoSenha())) {
            throw new RuntimeException("A senha e a confirmação da senha devem ser iguais");
        }
        String senhaCriptografada = passwordEncoder.encode(usuarioPasswordDTO.senha());
        var updatePassword = this.usuarioRepository.updatePassword(senhaCriptografada, id);
        if (updatePassword == 0) {
            throw new RuntimeException("Falha ao atualizar a senha");
        }
    }

    public void update(UsuarioRequestDTO usuarioDTO, Long id) {
        Optional<Usuario> usuarioOptionalCheck = this.usuarioRepository.findByEmail(usuarioDTO.email());
        if (usuarioOptionalCheck.isPresent() &&
                !usuarioOptionalCheck.get().getId().equals(id)) {
            throw new RuntimeException("email já existe");
        }
        usuarioOptionalCheck = this.usuarioRepository.findByLogin(usuarioDTO.login());
        if (usuarioOptionalCheck.isPresent() &&
                !usuarioOptionalCheck.get().getId().equals(id)) {
            throw new RuntimeException("login já existe");
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
            throw new RuntimeException("Usuario não encontrado");
        }
    }

    public void delete(Long id) {
        var delete = this.usuarioRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

}
