package br.com.fiap.fase1tc.restaurantes_backend.repositories;

import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByLogin(String login);
    List<Usuario> findAllByNome(String nome);
    Integer save(Usuario usuario);
    Integer updatePassword(String senha, Long id);
    Integer update(Usuario usuario, Long id);
    Integer delete(Long id);
}
