package br.com.fiap.fase1tc.restaurantes_backend.services.exceptions;

public class FalhaEmManipularUsuarioException extends RuntimeException {
    public FalhaEmManipularUsuarioException(String mensagem) {
        super(mensagem);
    }
}
