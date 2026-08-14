package br.com.fiap.fase1tc.restaurantes_backend.mappers;

import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;
import br.com.fiap.fase1tc.restaurantes_backend.entities.enums.PerfilUsuario;
import br.com.fiap.fase1tc.restaurantes_backend.factories.UsuarioFactory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMapper implements RowMapper<Usuario> {
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Usuario usuario = UsuarioFactory.createUsuario(PerfilUsuario.valueOf(rs.getString("perfil")));
        usuario.setId(rs.getLong("id"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setLogin(rs.getString("login"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setLogradouro(rs.getString("logradouro"));
        usuario.setNumero(rs.getString("numero"));
        usuario.setComplemento(rs.getString("complemento"));
        usuario.setBairro(rs.getString("bairro"));
        usuario.setCidade(rs.getString("cidade"));
        usuario.setEstado(rs.getString("estado"));
        usuario.setCep(rs.getString("cep"));
        usuario.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
        usuario.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
        return usuario;
    }
}
