package br.com.fiap.fase1tc.restaurantes_backend.controllers;

import br.com.fiap.fase1tc.restaurantes_backend.entities.Usuario;
import br.com.fiap.fase1tc.restaurantes_backend.services.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuarios(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("... acessando o endpoint de usuários /usuarios...");
        var usuarios = this.usuarioService.findAllUsuarios(page, size);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> findUsuarioById(
            @PathVariable("id") long id
    ) {
        logger.info("/usuarios/" + id);
        var usuario = this.usuarioService.findUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<Usuario>> findByEmail(
            @PathVariable("email") String email
    ) {
        logger.info("/usuarios/email/" + email);
        var usuario = this.usuarioService.findUsuarioByEmail(email);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/nomedeusuario/{nomedeusuario}")
    public ResponseEntity<Optional<Usuario>> findByNomeDeUsuario(
            @PathVariable("nomedeusuario") String nomedeusuario
    ) {
        logger.info("/usuarios/nomedeusuario/" + nomedeusuario);
        var usuario = this.usuarioService.findUsuarioByNomeDeUsuario(nomedeusuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Void> saveUsuario(
            @RequestBody Usuario usuario
    ) {
        logger.info("POST -> /pessoas");
        this.usuarioService.saveUsuario(usuario);
        var status = HttpStatus.CREATED;
        return ResponseEntity.status(status.value()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUsuario(
            @PathVariable("id") Long id,
            @RequestBody Usuario usuario
    ) {
        logger.info("PUT -> /usuarios/" + id);
        this.usuarioService.updateUsuario(usuario, id);
        var status = HttpStatus.NO_CONTENT;
        return ResponseEntity.status(status.value()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(
            @PathVariable("id") Long id
    ) {
        logger.info("DELETE -> /usuarios/" + id);
        this.usuarioService.deleteUsuario(id);
        return ResponseEntity.ok().build();
    }
}
