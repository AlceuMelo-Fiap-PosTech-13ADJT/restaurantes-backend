package br.com.fiap.fase1tc.restaurantes_backend.repositories;

import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
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
                .query(Usuario.class)
                .optional();
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return this.jdbcClient
                .sql("SELECT * FROM usuarios WHERE email = :email")
                .param("email", email)
                .query(Usuario.class)
                .optional();
    }

    @Override
    public Optional<Usuario> findByNomeDeUsuario(String nomeDeUsuario) {
        return this.jdbcClient
                .sql("SELECT * FROM usuarios WHERE nome_de_usuario = :nome_de_usuario")
                .param("nome_de_usuario", nomeDeUsuario)
                .query(Usuario.class)
                .optional();
    }

    @Override
    public List<Usuario> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("SELECT * FROM usuarios LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Usuario.class)
                .list();
    }

    @Override
    public Integer save(Usuario usuario) {
        return this.jdbcClient
                .sql("INSERT INTO usuarios (nome, email, nome_de_usuario, senha, logradouro, numero, complemento, " +
                        "bairro, cidade, estado, cep, perfis, created_at, updated_at) VALUES (:nome, :email, " +
                        ":nome_de_usuario, :senha, :logradouro, :numero, :complemento, :bairro, :cidade, :estado, " +
                        ":cep, :perfis, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
                .param("nome", usuario.getNome())
                .param("email", usuario.getEmail())
                .param("nome_de_usuario", usuario.getNome_de_usuario())
                .param("senha", usuario.getSenha())
                .param("logradouro", usuario.getLogradouro())
                .param("numero", usuario.getNumero())
                .param("complemento", usuario.getComplemento())
                .param("bairro", usuario.getBairro())
                .param("cidade", usuario.getCidade())
                .param("estado", usuario.getEstado())
                .param("cep", usuario.getCep())
                .param("perfis", usuario.getPerfis(true))
                .update();
    }

    @Override
    public Integer update(Usuario usuario, Long id) {
        return this.jdbcClient
                .sql("UPDATE usuarios SET nome = :nome, email = :email, nome_de_usuario = :nome_de_usuario, " +
                        "senha = :senha, logradouro = :logradouro, numero = :numero, complemento = :complemento, " +
                        "bairro = :bairro, cidade = :cidade, estado = :estado, cep = :cep, perfis = :perfis, " +
                        "updated_at = CURRENT_TIMESTAMP WHERE id = :id")
                .param("nome", usuario.getNome())
                .param("email", usuario.getEmail())
                .param("nome_de_usuario", usuario.getNome_de_usuario())
                .param("senha", usuario.getSenha())
                .param("logradouro", usuario.getLogradouro())
                .param("numero", usuario.getNumero())
                .param("complemento", usuario.getComplemento())
                .param("bairro", usuario.getBairro())
                .param("cidade", usuario.getCidade())
                .param("estado", usuario.getEstado())
                .param("cep", usuario.getCep())
                .param("perfis", usuario.getPerfis())
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
