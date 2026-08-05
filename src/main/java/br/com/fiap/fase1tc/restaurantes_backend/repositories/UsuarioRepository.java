package br.com.fiap.fase1tc.restaurantes_backend.repositories;

import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;
import br.com.fiap.fase1tc.restaurantes_backend.mappers.UsuarioMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements IUsuarioRepository {

    private final JdbcClient jdbcClient;

    public UsuarioRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return this.jdbcClient
                .sql("SELECT * FROM usuarios WHERE id = :id")
                .param("id", id)
                .query(new UsuarioMapper())
                .optional();
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return this.jdbcClient
                .sql("SELECT * FROM usuarios WHERE email = :email")
                .param("email", email)
                .query(new UsuarioMapper())
                .optional();
    }

    @Override
    public Optional<Usuario> findByLogin(String login) {
        return this.jdbcClient
                .sql("SELECT * FROM usuarios WHERE login = :login")
                .param("login", login)
                .query(new UsuarioMapper())
                .optional();
    }

    @Override
    public List<Usuario> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("SELECT * FROM usuarios LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(new UsuarioMapper())
                .list();
    }

    @Override
    public Integer save(Usuario usuario) {
        if (this.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("email já existe");
        }
        if (this.findByLogin(usuario.getLogin()).isPresent()) {
            throw new IllegalArgumentException("nome de usuário já existe");
        }
        return this.jdbcClient
                .sql("INSERT INTO usuarios (nome, email, login, senha, logradouro, numero, complemento, " +
                        "bairro, cidade, estado, cep, perfil, created_at, updated_at) VALUES (:nome, :email, " +
                        ":login, :senha, :logradouro, :numero, :complemento, :bairro, :cidade, :estado, " +
                        ":cep, :perfil, :created_at, :updated_at)")
                .param("nome", usuario.getNome())
                .param("email", usuario.getEmail())
                .param("login", usuario.getLogin())
                .param("senha", usuario.getSenha())
                .param("logradouro", usuario.getLogradouro())
                .param("numero", usuario.getNumero())
                .param("complemento", usuario.getComplemento())
                .param("bairro", usuario.getBairro())
                .param("cidade", usuario.getCidade())
                .param("estado", usuario.getEstado())
                .param("cep", usuario.getCep())
                .param("perfil", usuario.getPerfil().name())
                .param("created_at", LocalDateTime.now())
                .param("updated_at", LocalDateTime.now())
                .update();
    }

    @Override
    public Integer update(Usuario usuario, Long id) {
        Optional<Usuario> usuarioOptionalCheck = this.findByEmail(usuario.getEmail());
        if (usuarioOptionalCheck.isPresent() &&
                !usuarioOptionalCheck.get().getId().equals(id)) {
            throw new IllegalArgumentException("email já existe");
        }
        usuarioOptionalCheck = this.findByLogin(usuario.getLogin());
        if (usuarioOptionalCheck.isPresent() &&
                !usuarioOptionalCheck.get().getId().equals(id)) {
            throw new IllegalArgumentException("nome de usuário já existe");
        }
        return this.jdbcClient
                .sql("UPDATE usuarios SET nome = :nome, email = :email, login = :login, " +
                        "logradouro = :logradouro, numero = :numero, complemento = :complemento, " +
                        "bairro = :bairro, cidade = :cidade, estado = :estado, cep = :cep, perfil = :perfil, " +
                        "updated_at = :updated_at WHERE id = :id")
                .param("nome", usuario.getNome())
                .param("email", usuario.getEmail())
                .param("login", usuario.getLogin())
                .param("logradouro", usuario.getLogradouro())
                .param("numero", usuario.getNumero())
                .param("complemento", usuario.getComplemento())
                .param("bairro", usuario.getBairro())
                .param("cidade", usuario.getCidade())
                .param("estado", usuario.getEstado())
                .param("cep", usuario.getCep())
                .param("perfil", usuario.getPerfil().name())
                .param("updated_at", LocalDateTime.now())
                .param("id", id)
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM usuarios WHERE id = :id")
                .param("id", id)
                .update();
    }

}
