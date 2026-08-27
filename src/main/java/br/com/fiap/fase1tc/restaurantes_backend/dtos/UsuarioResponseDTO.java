package br.com.fiap.fase1tc.restaurantes_backend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de salvamento de usuário bem-sucedido")
public record UsuarioResponseDTO(
        @Schema(description = "Id", example = "1", required = true)
        Long id,

        @Schema(description = "Nome completo", example = "João Silva", required = true)
        String nome,

        @Schema(description = "E-mail único", example = "joao@email.com", required = true)
        String email
) {
}
