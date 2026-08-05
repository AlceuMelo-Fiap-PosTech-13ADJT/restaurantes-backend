package br.com.fiap.fase1tc.restaurantes_backend.dtos;

public record UsuarioPasswordRequestDTO(
    String senha,
    String confirmacaoSenha
) {
}
