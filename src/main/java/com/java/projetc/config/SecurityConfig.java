package com.java.projetc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig
 * Configuração de segurança para a aplicação
 *
 * Implementa:
 * - A01: Access Control
 * - A04: CSRF Protection
 * - A07: Authentication & Strong Password Hashing
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * PasswordEncoder Bean
     * Fornece BCryptPasswordEncoder para hash de senhas
     *
     * @return PasswordEncoder com BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * SecurityFilterChain Bean
     * Configura as regras de segurança HTTP
     *
     * Configurações:
     * - Acesso público: home, produtos, sobre, contato, login, cadastro
     * - Acesso protegido: carrinho, perfil
     * - Acesso ADMIN: /admin/**
     * - Headers de segurança: X-Frame-Options, X-Content-Type-Options, Strict-Transport-Security
     * - CSRF Protection habilitado
     * - Session Management seguro
     *
     * @param http - HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception - erro na configuração
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // ============ Autorização (A01) ============
            .authorizeHttpRequests(authz -> authz
                // Rotas públicas
                .requestMatchers("/", "/produtos", "/sobre", "/contato").permitAll()
                .requestMatchers("/login", "/cadastro", "/css/**", "/js/**").permitAll()
                .requestMatchers("/error/**").permitAll()
                
                // Rotas protegidas (CUSTOMER ou ADMIN)
                .requestMatchers("/carrinho", "/perfil").authenticated()
                
                // Rotas de ADMIN
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
                // H2 Console bloqueado (A03: Log & Monitor)
                .requestMatchers("/h2-console/**").denyAll()
                
                // Qualquer outra requisição deve ser autenticada
                .anyRequest().authenticated()
            )

            // ============ Login & Logout ============
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            
            // ============ Tratamento de exceções ============
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("/login");
                })
                .accessDeniedPage("/error/403")
            )

            // ============ CSRF Protection (A04) ============
            // CSRF está HABILITADO por padrão no Spring Security 6.x
            // O token é automaticamente gerado em formulários Thymeleaf com sec:csrf

            // ============ Headers de Segurança ============
            .headers(headers -> headers
                // X-Frame-Options: DENY (previne Clickjacking)
                .frameOptions(frameOptions -> frameOptions.deny())
                
                // X-Content-Type-Options: nosniff (previne MIME type sniffing)
                .contentTypeOptions(contentTypeOptions -> {})
                
                // Strict-Transport-Security (HSTS)
                .httpStrictTransportSecurity(hsts -> hsts
                    .includeSubDomains(true)
                    .preload(true)
                    .maxAgeInSeconds(31536000) // 1 ano
                )
            )

            // ============ Session Management (A07) ============
            // HttpOnly e Secure cookies são configurados em application.properties
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/login")
                .maximumSessions(1) // Máximo 1 sessão por usuário
                .expiredUrl("/login")
            );

        return http.build();
    }
}




