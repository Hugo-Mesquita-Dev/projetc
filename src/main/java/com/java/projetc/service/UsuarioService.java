package com.java.projetc.service;

import com.java.projetc.model.Usuario;
import com.java.projetc.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import java.util.Optional;

/**
 * UsuarioService
 * Serviço de negócio para usuários
 *
 * Implementa:
 * - A07: Strong password hashing
 * - Validação de dados
 * - Controle de acesso
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Cadastro seguro de novo usuário
     *
     * - Valida se email já existe
     * - Faz hash da senha com BCrypt
     * - Limpa campo de senha em texto plano
     *
     * @param usuario - dados do usuário (com senhaPlana preenchida)
     * @return usuário persistido
     * @throws IllegalArgumentException se email já existe ou dados inválidos
     */
    public Usuario cadastrar(@Valid Usuario usuario) {
        // Validar se email já existe
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado: " + usuario.getEmail());
        }

        // NUNCA persistir senhaPlana
        // Fazer hash da senha
        usuario.setSenha(passwordEncoder.encode(usuario.getSenhaPlana()));
        usuario.setSenhaPlana(null); // Limpar campo transient

        // Definir role padrão
        usuario.setRole(Usuario.Role.CUSTOMER);

        return usuarioRepository.save(usuario);
    }

    /**
     * Atualizar dados do usuário (sem alterar senha)
     *
     * @param id - ID do usuário
     * @param usuarioAtualizado - dados atualizados
     * @return usuário atualizado
     * @throws IllegalArgumentException se usuário não existe
     */
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + id));

        // Atualizar apenas campos permitidos (não senha!)
        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setTelefone(usuarioAtualizado.getTelefone());
        usuario.setEndereco(usuarioAtualizado.getEndereco());

        return usuarioRepository.save(usuario);
    }

    /**
     * Buscar usuário por email
     *
     * @param email - email do usuário
     * @return usuário encontrado
     * @throws IllegalArgumentException se não encontrado
     */
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + email));
    }

    /**
     * Buscar usuário por ID
     *
     * @param id - ID do usuário
     * @return usuário encontrado
     */
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Verificar se email existe
     *
     * @param email - email a verificar
     * @return true se existe
     */
    public boolean existsEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    /**
     * Atualizar apenas endereço (usado no carrinho)
     *
     * @param id - ID do usuário
     * @param endereco - novo endereço
     */
    public void atualizarEndereco(Long id, String endereco) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        usuario.setEndereco(endereco);
        usuarioRepository.save(usuario);
    }
}

