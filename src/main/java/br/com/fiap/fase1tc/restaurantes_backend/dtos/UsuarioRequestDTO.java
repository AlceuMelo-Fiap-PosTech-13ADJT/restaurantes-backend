package br.com.fiap.fase1tc.restaurantes_backend.dtos;

import br.com.fiap.fase1tc.restaurantes_backend.entities.enums.PerfilUsuario;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para cadastro ou atualização de usuário")
public record UsuarioRequestDTO(
        @Schema(description = "Nome completo", example = "João Silva", required = false)
        String nome,

        @Schema(description = "E-mail único", example = "joao@email.com", required = false)
        String email,

        @Schema(description = "Nome de login", example = "joaosilva", required = false)
        String login,

        @Schema(description = "Senha do usuário", example = "SenhaForte123!", required = false)
        String senha,

        @Schema(description = "Confirmação da senha", example = "SenhaForte123!", required = false)
        String confirmacaoSenha,

        @Schema(description = "Logradouro", example = "Rua Principal", required = false)
        String logradouro,

        @Schema(description = "Número", example = "100", required = false)
        String numero,

        @Schema(description = "Complemento", example = "Apto 12", required = false)
        String complemento,

        @Schema(description = "Bairro", example = "Centro", required = false)
        String bairro,

        @Schema(description = "Cidade", example = "São Paulo", required = false)
        String cidade,

        @Schema(description = "Estado (UF)", example = "SP", required = false)
        String estado,

        @Schema(description = "CEP", example = "01001-000", required = false)
        String cep,

        @Schema(description = "Perfil do usuário", allowableValues = {"CLIENTE", "PROPRIETARIO"}, example = "CLIENTE", required = false)
        PerfilUsuario perfil
) {}