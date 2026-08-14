package br.com.fiap.fase1tc.restaurantes_backend.dtos;

import br.com.fiap.fase1tc.restaurantes_backend.entities.enums.PerfilUsuario;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para cadastro ou atualização de usuário")
public record UsuarioRequestDTO(
        @Schema(description = "Nome completo", example = "João Silva", required = true)
        String nome,

        @Schema(description = "E-mail único", example = "joao@email.com", required = true)
        String email,

        @Schema(description = "Nome de login", example = "joaosilva", required = true)
        String login,

        @Schema(description = "Senha do usuário", example = "SenhaForte123!", required = true)
        String senha,

        @Schema(description = "Confirmação da senha", example = "SenhaForte123!", required = true)
        String confirmacaoSenha,

        @Schema(description = "Logradouro", example = "Rua Principal")
        String logradouro,

        @Schema(description = "Número", example = "100")
        String numero,

        @Schema(description = "Complemento", example = "Apto 12")
        String complemento,

        @Schema(description = "Bairro", example = "Centro")
        String bairro,

        @Schema(description = "Cidade", example = "São Paulo")
        String cidade,

        @Schema(description = "Estado (UF)", example = "SP")
        String estado,

        @Schema(description = "CEP", example = "01001-000")
        String cep,

        @Schema(description = "Perfil do usuário", allowableValues = {"CLIENTE", "PROPRIETARIO"}, example = "CLIENTE")
        PerfilUsuario perfil
) {}