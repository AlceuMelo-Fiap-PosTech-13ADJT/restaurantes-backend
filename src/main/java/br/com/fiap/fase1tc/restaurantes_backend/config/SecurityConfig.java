package br.com.fiap.fase1tc.restaurantes_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Mantém o Bean para o UsuarioService usar o BCrypt
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configura o Spring Security para não bloquear os endpoints
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Desabilita o CSRF (necessário para APIs REST que usam POST/PUT)
            .csrf(csrf -> csrf.disable())
            // Permite que todas as requisições acessem qualquer endpoint
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().permitAll()
            );
        return http.build();
    }
}
