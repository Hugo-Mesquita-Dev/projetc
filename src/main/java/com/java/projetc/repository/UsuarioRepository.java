package com.java.projetc.repository;

import com.java.projetc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository para Usuario
 * Fornece métodos de acesso ao banco de dados
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Buscar usuário por email
     * Usado na autenticação
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Verificar se email já existe
     * Usado no cadastro para evitar duplicatas
     */
    boolean existsByEmail(String email);
}

