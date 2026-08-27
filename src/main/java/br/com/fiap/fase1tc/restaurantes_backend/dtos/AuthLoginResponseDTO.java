package br.com.fiap.fase1tc.restaurantes_backend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de login bem-sucedido")
public record AuthLoginResponseDTO(
        @Schema(description = "Login do usuário", example = "usuario_exemplo")
        String login,

        @Schema(description = "Token de acesso", example = "eyJhbGci...")
        String token
) {}
