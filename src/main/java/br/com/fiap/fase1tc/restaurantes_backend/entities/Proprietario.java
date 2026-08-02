package br.com.fiap.fase1tc.restaurantes_backend.entities;

import br.com.fiap.fase1tc.restaurantes_backend.entities.enums.PerfilUsuario;

public class Proprietario extends Usuario {
    public Proprietario() {
        super(PerfilUsuario.PROPRIETARIO);
    }
}
