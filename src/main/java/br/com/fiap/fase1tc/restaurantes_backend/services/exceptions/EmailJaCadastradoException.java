package br.com.fiap.fase1tc.restaurantes_backend.services.exceptions;

public class EmailJaCadastradoException extends RuntimeException {
    public EmailJaCadastradoException(String email) {
        super("O email \"" + email + "\" já está em uso, escolha um diferente.");
    }
}
