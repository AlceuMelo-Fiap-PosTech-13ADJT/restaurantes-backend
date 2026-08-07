package br.com.fiap.fase1tc.restaurantes_backend.services.exceptions;

public class LoginJaCadastradoException extends RuntimeException {
    public LoginJaCadastradoException(String login) {
        super("O login \"" + login + "\" já está em uso, escolha um diferente.");
    }
}
