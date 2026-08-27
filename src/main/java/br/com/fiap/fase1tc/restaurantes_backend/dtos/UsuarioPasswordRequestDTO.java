package br.com.fiap.fase1tc.restaurantes_backend.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para alteração de senha")
public record UsuarioPasswordRequestDTO(
        @Schema(description = "Senha nova", example = "NovaSenha123!", required = true)
        String senha,

        @Schema(description = "Confirmação da nova senha", example = "NovaSenha123!", required = true)
        String confirmacaoSenha
) {}
