package br.com.fiap.fase1tc.restaurantes_backend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Credenciais para autenticação")
public record AuthLoginRequestDTO(
        @Schema(description = "Login do usuário", example = "usuario_exemplo", required = true)
        String login,

        @Schema(description = "Senha do usuário", example = "senha123", required = true)
        String senha
) {}
