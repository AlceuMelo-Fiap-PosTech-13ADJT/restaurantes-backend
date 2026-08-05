package br.com.fiap.fase1tc.restaurantes_backend.dtos;

import br.com.fiap.fase1tc.restaurantes_backend.entities.enums.PerfilUsuario;

public record UsuarioRequestDTO(
    String nome,
    String email,
    String login,
    String senha,
    String confirmacaoSenha,
    String logradouro,
    String numero,
    String complemento,
    String bairro,
    String cidade,
    String estado,
    String cep,
    PerfilUsuario perfil
) {
}
