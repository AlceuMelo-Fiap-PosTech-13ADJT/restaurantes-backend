package br.com.fiap.fase1tc.restaurantes_backend.services.exceptions;

public class SenhaEConfirmacaoDiferentesException extends RuntimeException {
    public SenhaEConfirmacaoDiferentesException() {
        super("A senha e a confirmação de senha informados são diferentes, devem ser iguais.");
    }
}
