package br.com.fiap.fase1tc.restaurantes_backend.controllers;

import br.com.fiap.fase1tc.restaurantes_backend.dtos.AuthLoginRequestDTO;
import br.com.fiap.fase1tc.restaurantes_backend.dtos.UsuarioResponseDTO;
import br.com.fiap.fase1tc.restaurantes_backend.dtos.UsuarioResponseFindDTO;
import br.com.fiap.fase1tc.restaurantes_backend.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Autenticação", description = "Endpoints para autenticação de usuários")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
        operationId = "validaCredenciais",
        summary = "Valida login e senha",
        description = "Recebe as credenciais do usuário e retorna status de sucesso ou erro de autenticação.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário ou senha incorretos",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = @ExampleObject(value = "{\"type\":\"about:blank\",\"title\":\"Unauthorized\",\"status\":401,\"detail\":\"Credenciais incorretas\",\"instance\":\"/api/v1/auth/login\"}")
                )}
            ),
            @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProblemDetail.class),
                    examples = @ExampleObject(value = "{\"type\":\"about:blank\",\"title\":\"Bad Request\",\"status\":400,\"detail\":\"Dados de requisição inválidos\",\"instance\":\"/api/v1/auth/login\"}")
                )}
            )
        }
    )
    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseFindDTO> login(@RequestBody AuthLoginRequestDTO authLoginDTO) {
        logger.info("POST -> /api/v1/auth/login");
        return ResponseEntity.status(HttpStatus.OK).body(this.authService.loginAuth(authLoginDTO));
    }
}
