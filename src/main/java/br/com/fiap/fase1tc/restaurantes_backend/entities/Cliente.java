package br.com.fiap.fase1tc.restaurantes_backend.entities;

import br.com.fiap.fase1tc.restaurantes_backend.entities.enums.PerfilUsuario;

public class Cliente extends Usuario {
    public Cliente() {
        super(PerfilUsuario.CLIENTE);
    }
}
