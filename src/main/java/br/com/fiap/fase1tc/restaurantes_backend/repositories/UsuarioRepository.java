package br.com.fiap.fase1tc.restaurantes_backend.repositories;

import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;
import br.com.fiap.fase1tc.restaurantes_backend.mappers.UsuarioMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

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
    public Optional<Usuario> findByNomeDeUsuario(String nomeDeUsuario) {
        return this.jdbcClient
                .sql("SELECT * FROM usuarios WHERE nome_de_usuario = :nome_de_usuario")
                .param("nome_de_usuario", nomeDeUsuario)
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
        if (this.findByNomeDeUsuario(usuario.getNome_de_usuario()).isPresent()) {
            throw new IllegalArgumentException("nome de usuário já existe");
        }
        return this.jdbcClient
                .sql("INSERT INTO usuarios (nome, email, nome_de_usuario, senha, logradouro, numero, complemento, " +
                        "bairro, cidade, estado, cep, perfil, created_at, updated_at) VALUES (:nome, :email, " +
                        ":nome_de_usuario, :senha, :logradouro, :numero, :complemento, :bairro, :cidade, :estado, " +
                        ":cep, :perfil, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
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
                .param("perfil", usuario.getPerfil().name())
                .update();
    }

    @Override
    public Integer update(Usuario usuario, Long id) {
        Optional<Usuario> usuarioOptionalCheck = this.findByEmail(usuario.getEmail());
        if (usuarioOptionalCheck.isPresent() &&
                !usuarioOptionalCheck.get().getId().equals(id)) {
            throw new IllegalArgumentException("email já existe");
        }
        usuarioOptionalCheck = this.findByNomeDeUsuario(usuario.getNome_de_usuario());
        if (usuarioOptionalCheck.isPresent() &&
                !usuarioOptionalCheck.get().getId().equals(id)) {
            throw new IllegalArgumentException("nome de usuário já existe");
        }
        return this.jdbcClient
                .sql("UPDATE usuarios SET nome = :nome, email = :email, nome_de_usuario = :nome_de_usuario, " +
                        "senha = :senha, logradouro = :logradouro, numero = :numero, complemento = :complemento, " +
                        "bairro = :bairro, cidade = :cidade, estado = :estado, cep = :cep, perfil = :perfil, " +
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
                .param("perfil", usuario.getPerfil().name())
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
