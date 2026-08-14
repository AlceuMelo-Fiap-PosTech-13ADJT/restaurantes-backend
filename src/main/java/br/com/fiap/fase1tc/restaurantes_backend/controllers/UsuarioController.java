package br.com.fiap.fase1tc.restaurantes_backend.controllers;

import br.com.fiap.fase1tc.restaurantes_backend.dtos.UsuarioPasswordRequestDTO;
import br.com.fiap.fase1tc.restaurantes_backend.dtos.UsuarioRequestDTO;
import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;
import br.com.fiap.fase1tc.restaurantes_backend.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestão de Usuários", description = "Endpoints para operações de perfil e usuários")
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(
        summary = "Listar usuários por nome",
        description = "Retorna uma lista de usuários filtrada pelo nome informado.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetro de busca inválido",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = @ExampleObject(value = "{\"title\":\"Bad Request\",\"status\":400,\"detail\":\"O parâmetro 'nome' é obrigatório\"}"))})
        }
    )
    @GetMapping
    public ResponseEntity<List<Usuario>> findAllByNome(
            @Parameter(description = "Nome parcial para filtragem") @RequestParam("nome") String nome
    ) {
        logger.info("GET -> /api/v1/usuarios?nome=" + nome);
        var usuarios = this.usuarioService.findAllByNome(nome);
        return ResponseEntity.ok(usuarios);
    }

    @Operation(
        summary = "Cadastrar novo usuário",
        description = "Realiza a inserção de um novo usuário no banco de dados.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "E-mail ou login já cadastrado",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = @ExampleObject(value = "{\"title\":\"Conflict\",\"status\":409,\"detail\":\"O e-mail e/ou login informado já está em uso\"}")
                )}
            )
        }
    )
    @PostMapping
    public ResponseEntity<Void> save(
            @RequestBody UsuarioRequestDTO usuarioDTO
    ) {
        logger.info("POST -> /api/v1/usuarios");
        this.usuarioService.save(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
        summary = "Atualizar senha",
        description = "Altera a senha de um usuário específico.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso"),
                @ApiResponse(responseCode = "400", description = "Dados inválidos ou falha na atualização", // 400 para senhas diferentes é aceitável
                        content = {@Content(mediaType = "application/json",
                                schema = @Schema(implementation = ProblemDetail.class),
                                examples = @ExampleObject(value = "{\"title\":\"Bad Request\",\"status\":400,\"detail\":\"As senhas não conferem\"}")
                        )}
                )
        }
    )
    @PutMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(
            @Parameter(description = "ID do usuário") @PathVariable("id") Long id,
            @RequestBody UsuarioPasswordRequestDTO usuarioPasswordDTO
    ) {
        logger.info("PUT -> /api/v1/usuarios/" + id + "/password");
        this.usuarioService.updatePassword(usuarioPasswordDTO, id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Atualizar dados do usuário",
        description = "Atualiza as informações cadastrais de um usuário existente.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Usuário atualizado com sucesso"),
                @ApiResponse(responseCode = "409", description = "Conflito ao atualizar dados",
                    content = {@Content(mediaType = "application/json",
                        schema = @Schema(implementation = ProblemDetail.class),
                        examples = @ExampleObject(value = "{\"title\":\"Conflict\",\"status\":409,\"detail\":\"Dados de validação falharam ou duplicidade\"}")
                    )}
                )
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @Parameter(description = "ID do usuário") @PathVariable("id") Long id,
            @RequestBody UsuarioRequestDTO usuarioDTO
    ) {
        logger.info("PUT -> /api/v1/usuarios/" + id);
        this.usuarioService.update(usuarioDTO, id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Excluir usuário",
        description = "Remove um usuário do sistema permanentemente.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = @ExampleObject(value = "{\"title\":\"Not Found\",\"status\":404,\"detail\":\"Usuário com ID não encontrado\"}")
                )}
            )
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do usuário") @PathVariable("id") Long id
    ) {
        logger.info("DELETE -> /api/v1/usuarios/" + id);
        this.usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}