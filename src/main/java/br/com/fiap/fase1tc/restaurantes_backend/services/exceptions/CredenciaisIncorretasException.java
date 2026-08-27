package br.com.fiap.fase1tc.restaurantes_backend.services.exceptions;

public class CredenciaisIncorretasException extends RuntimeException {
    public CredenciaisIncorretasException(String message) {
        super(message);
    }
}
