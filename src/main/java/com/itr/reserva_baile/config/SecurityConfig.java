package com.itr.reserva_baile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactivar CSRF si es necesario
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Permitir acceso a Swagger
                        .requestMatchers("/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(
                            "/clases/**", 
                            "/estudios/**", 
                            "/reservas/**",
                            "/resenias/**",
                            "/membresias/**",
                            "/notificaciones/**",
                            "/instructores/**",
                            "/pagos/**",
                            "/paquetes/**"
                        ).authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(httpBasicCustomizer -> {}); 

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}password") // "{noop}" evita el encoding del password (solo para desarrollo)
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}