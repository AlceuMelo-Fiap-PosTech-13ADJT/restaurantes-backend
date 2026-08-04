package br.com.fiap.fase1tc.restaurantes_backend.services;

import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;
import br.com.fiap.fase1tc.restaurantes_backend.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAllUsuarios(int page, int size) {
        int offset = (page - 1) * size;
        return this.usuarioRepository.findAll(size, offset);
    }

    public Optional<Usuario> findUsuarioById(Long id) {
        return this.usuarioRepository.findById(id);
    }

    public Optional<Usuario> findUsuarioByLogin(String login) {
        return this.usuarioRepository.findByLogin(login);
    }

    public Optional<Usuario> findUsuarioByEmail(String email) {
        return this.usuarioRepository.findByEmail(email);
    }

    public void saveUsuario(Usuario usuario) {
        var save = this.usuarioRepository.save(usuario);
        Assert.state(save == 1, "Erro ao salvar usuário " +  usuario.getNome());
    }

    public void updateUsuario(Usuario usuario, Long id) {
        var update = this.usuarioRepository.update(usuario, id);
        if (update == 0) {
            throw new RuntimeException("Usuario não encontrado");
        }
    }

    public void deleteUsuario(Long id) {
        var delete = this.usuarioRepository.delete(id);
        if (delete == 0) {
            throw new RuntimeException("Usuário não encontrado");
        }
    }

}
