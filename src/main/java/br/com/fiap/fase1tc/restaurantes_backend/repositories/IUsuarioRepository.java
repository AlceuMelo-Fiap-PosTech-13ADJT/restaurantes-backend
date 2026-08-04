package br.com.fiap.fase1tc.restaurantes_backend.repositories;

import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository {
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByLogin(String login);
    List<Usuario> findAll(int size, int page);
    Integer save(Usuario usuario);
    Integer update(Usuario usuario, Long id);
    Integer delete(Long id);
}
