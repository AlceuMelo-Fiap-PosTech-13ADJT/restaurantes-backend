package br.com.fiap.fase1tc.restaurantes_backend.entities;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Usuario {
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
    private String[] perfis;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public String getPerfis(Boolean formatado) {
        if (!formatado) {
            return null;
        }
        return "[" + String.join(",", perfis) + "]";
    }

    public void setPerfis(String perfis) {
        if (perfis == null || perfis.isEmpty() || perfis.equals("[]")) {
            this.perfis = new String[0];
        } else {
            this.perfis = perfis.substring(1, perfis.length() - 1).split(",");
        }
    }
}
