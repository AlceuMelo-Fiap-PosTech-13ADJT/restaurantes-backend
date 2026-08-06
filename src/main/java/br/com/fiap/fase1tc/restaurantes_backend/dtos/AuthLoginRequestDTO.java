package br.com.fiap.fase1tc.restaurantes_backend.dtos;

public record AuthLoginRequestDTO(
        String login,
        String senha
) {
}
