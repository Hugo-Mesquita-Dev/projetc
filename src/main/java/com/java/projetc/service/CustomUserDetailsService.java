package com.java.projetc.service;

import com.java.projetc.model.Usuario;
import com.java.projetc.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;

/**
 * CustomUserDetailsService
 * Integra Spring Security com nosso banco de dados
 *
 * Implementa: A07 - Authentication Failures
 * Fornece UserDetails para autenticação
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Carrega usuário por email (username)
     * Chamado pelo Spring Security durante login
     *
     * @param email - email do usuário
     * @return UserDetails com dados do usuário
     * @throws UsernameNotFoundException se email não existe
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado: " + email));

        // Construir autoridades (roles)
        Collection<GrantedAuthority> authorities = Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name())
        );

        // Retornar UserDetails para Spring Security
        return org.springframework.security.core.userdetails.User
            .withUsername(usuario.getEmail())
            .password(usuario.getSenha()) // Já hashed com BCrypt
            .authorities(authorities)
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build();
    }
}

