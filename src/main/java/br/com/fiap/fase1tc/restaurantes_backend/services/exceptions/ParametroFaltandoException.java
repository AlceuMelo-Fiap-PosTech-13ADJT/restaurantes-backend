package br.com.fiap.fase1tc.restaurantes_backend.services.exceptions;

public class ParametroFaltandoException extends RuntimeException {
    public ParametroFaltandoException(String message) {
        super(message);
    }
}
