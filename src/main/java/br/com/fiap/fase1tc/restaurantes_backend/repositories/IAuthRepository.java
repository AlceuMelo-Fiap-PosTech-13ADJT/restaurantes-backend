package br.com.fiap.fase1tc.restaurantes_backend.repositories;

public interface IAuthRepository {
    Boolean loginAuth(String login, String senha);
}
