package br.com.fiap.fase1tc.restaurantes_backend.dtos;

import br.com.fiap.fase1tc.restaurantes_backend.entities.enums.PerfilUsuario;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UsuarioRequestDTO {
    private Long id;
    private String nome;
    private String email;
    private String nome_de_usuario;
    private String senha;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private PerfilUsuario perfil;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
