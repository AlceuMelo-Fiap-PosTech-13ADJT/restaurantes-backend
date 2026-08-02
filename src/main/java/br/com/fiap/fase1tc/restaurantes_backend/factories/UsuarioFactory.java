package br.com.fiap.fase1tc.restaurantes_backend.factories;

import br.com.fiap.fase1tc.restaurantes_backend.entities.Cliente;
import br.com.fiap.fase1tc.restaurantes_backend.entities.enums.PerfilUsuario;
import br.com.fiap.fase1tc.restaurantes_backend.entities.Proprietario;
import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;

public class UsuarioFactory {
    public static Usuario createUsuario(PerfilUsuario perfil) {
        Usuario usuario = null;
        if (perfil == PerfilUsuario.CLIENTE) {
            usuario = new Cliente();
        } else if (perfil == PerfilUsuario.PROPRIETARIO) {
            usuario = new Proprietario();
        } else {
            throw new IllegalArgumentException("Perfil de usuário inválido");
        }
        return usuario;
    }
}
