package br.com.fiap.fase1tc.restaurantes_backend.controllers.handlers;

import br.com.fiap.fase1tc.restaurantes_backend.services.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ProblemDetail handleEmailJaCadastradoException(EmailJaCadastradoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
        problemDetail.setTitle("Email já cadastrado");
        problemDetail.setType(java.net.URI.create("https://backend.restaurantes.fiap/errors/email-ja-cadastrado"));
        return problemDetail;
    }

    @ExceptionHandler(LoginJaCadastradoException.class)
    public ProblemDetail handleLoginJaCadastradoException(LoginJaCadastradoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
        problemDetail.setTitle("Login já cadastrado");
        problemDetail.setType(java.net.URI.create("https://backend.restaurantes.fiap/errors/login-ja-cadastrado"));
        return problemDetail;
    }

    @ExceptionHandler(SenhaEConfirmacaoDiferentesException.class)
    public ProblemDetail handleSenhaEConfirmacaoDiferentesException(SenhaEConfirmacaoDiferentesException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
        problemDetail.setTitle("Senha e confirmação de senha diferentes");
        problemDetail.setType(java.net.URI.create("https://backend.restaurantes.fiap/errors/senha-e-confirmacao-diferentes"));
        return problemDetail;
    }

    @ExceptionHandler(FalhaEmManipularUsuarioException.class)
    public ProblemDetail handleFalhaEmManipularUsuarioException(FalhaEmManipularUsuarioException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        problemDetail.setTitle("Falha em manipular usuário");
        problemDetail.setType(java.net.URI.create("https://backend.restaurantes.fiap/errors/falha-em-manipular-usuario"));
        return problemDetail;
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ProblemDetail handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problemDetail.setTitle("Recurso não encontrado");
        problemDetail.setType(java.net.URI.create("https://backend.restaurantes.fiap/errors/not-found"));
        return problemDetail;
    }

}
