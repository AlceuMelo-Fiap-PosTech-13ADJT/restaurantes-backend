package br.com.fiap.fase1tc.restaurantes_backend.controllers;

import br.com.fiap.fase1tc.restaurantes_backend.dtos.UsuarioPasswordRequestDTO;
import br.com.fiap.fase1tc.restaurantes_backend.dtos.UsuarioRequestDTO;
import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;
import br.com.fiap.fase1tc.restaurantes_backend.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuario")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllByNome(
            @RequestParam("nome") String nome
    ) {
        logger.info("GET -> /v1/usuario?nome=" + nome);
        var usuarios = this.usuarioService.findAllByNome(nome);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Void> save(
            @RequestBody UsuarioRequestDTO usuarioDTO
    ) {
        logger.info("POST -> /v1/usuario");
        this.usuarioService.save(usuarioDTO);
        var status = HttpStatus.CREATED;
        return ResponseEntity.status(status.value()).build();
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(
            @PathVariable("id") Long id,
            @RequestBody UsuarioPasswordRequestDTO usuarioPasswordDTO
    ) {
        logger.info("POST -> /v1/usuario/" + id + "/password");
        this.usuarioService.updatePassword(usuarioPasswordDTO, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable("id") Long id,
            @RequestBody  UsuarioRequestDTO usuarioDTO
    ) {
        logger.info("PUT -> /v1/usuario/" + id);
        this.usuarioService.update(usuarioDTO, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    ) {
        logger.info("DELETE -> /v1/usuario/" + id);
        this.usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
