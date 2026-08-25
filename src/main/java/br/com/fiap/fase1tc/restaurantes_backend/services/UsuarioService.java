package br.com.fiap.fase1tc.restaurantes_backend.services;

import br.com.fiap.fase1tc.restaurantes_backend.dtos.UsuarioPasswordRequestDTO;
import br.com.fiap.fase1tc.restaurantes_backend.dtos.UsuarioRequestDTO;
import br.com.fiap.fase1tc.restaurantes_backend.dtos.UsuarioResponseFindDTO;
import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;
import br.com.fiap.fase1tc.restaurantes_backend.factories.UsuarioFactory;
import br.com.fiap.fase1tc.restaurantes_backend.repositories.IUsuarioRepository;
import br.com.fiap.fase1tc.restaurantes_backend.services.exceptions.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(IUsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponseFindDTO> findAllByNome(String nome) {
        List<Usuario> usuarios = usuarioRepository.findAllByNome(nome);
        return usuarios.stream()
                .map(u -> new UsuarioResponseFindDTO(
                        u.getId(),
                        u.getNome(),
                        u.getEmail(),
                        u.getLogin(),
                        u.getLogradouro(),
                        u.getNumero(),
                        u.getComplemento(),
                        u.getBairro(),
                        u.getCidade(),
                        u.getEstado(),
                        u.getCep(),
                        u.getPerfil()
                )).toList();
    }

    public Usuario save(UsuarioRequestDTO usuarioDTO) {
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
        Long generatedId = this.usuarioRepository.save(usuario);
        if (generatedId == null || generatedId <= 0) {
            throw new FalhaEmManipularUsuarioException("Erro ao salvar usuário " +  usuario.getNome() + ".");
        }
        usuario.setId(generatedId);
        return usuario;
    }

    public void updatePassword(UsuarioPasswordRequestDTO usuarioPasswordDTO, Long id) {
        Usuario usuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário com ID " + id + " não encontrado."));
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
        Usuario usuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário com ID " + id + " não encontrado."));
        if (usuarioDTO.email() != null && !usuarioDTO.email().equals(usuario.getEmail())) {
            if (this.usuarioRepository.findByEmail(usuarioDTO.email()).isPresent()) {
                throw new EmailJaCadastradoException(usuarioDTO.email());
            }
        }
        if (usuarioDTO.login() != null && !usuarioDTO.login().equals(usuario.getLogin())) {
            if (this.usuarioRepository.findByLogin(usuarioDTO.login()).isPresent()) {
                throw new LoginJaCadastradoException(usuarioDTO.login());
            }
        }
        if (usuarioDTO.nome() != null) usuario.setNome(usuarioDTO.nome());
        if (usuarioDTO.email() != null) usuario.setEmail(usuarioDTO.email());
        if (usuarioDTO.login() != null) usuario.setLogin(usuarioDTO.login());
        if (usuarioDTO.logradouro() != null) usuario.setLogradouro(usuarioDTO.logradouro());
        if (usuarioDTO.numero() != null) usuario.setNumero(usuarioDTO.numero());
        if (usuarioDTO.complemento() != null) usuario.setComplemento(usuarioDTO.complemento());
        if (usuarioDTO.bairro() != null) usuario.setBairro(usuarioDTO.bairro());
        if (usuarioDTO.cidade() != null) usuario.setCidade(usuarioDTO.cidade());
        if (usuarioDTO.estado() != null) usuario.setEstado(usuarioDTO.estado());
        if (usuarioDTO.cep() != null) usuario.setCep(usuarioDTO.cep());
        if (usuarioDTO.perfil() != null) usuario.setPerfil(usuarioDTO.perfil());
        var update = this.usuarioRepository.update(usuario, id);
        if (update == 0) {
            throw new FalhaEmManipularUsuarioException("Falha em atualizar o usuário ID " + id + ".");
        }
    }

    public void delete(Long id) {
        Usuario usuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário com ID " + id + " não encontrado."));
        var delete = this.usuarioRepository.delete(id);
        if (delete == 0) {
            throw new FalhaEmManipularUsuarioException("Falha em excluir o usuário id " + id + ".");
        }
    }

}
