package com.java.projetc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

/**
 * Entidade Usuario com segurança
 * - Senha com hash BCrypt
 * - Validação de entrada
 * - Roles/Permissions (RBAC)
 */
@Entity
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100)
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Senha já hashed com BCrypt
     * NUNCA armazenar em texto plano
     */
    @NotBlank
    @Column(nullable = false)
    private String senha;

    /**
     * Campo transient para validação de entrada
     * Usado apenas durante cadastro/atualização
     * NÃO persiste no banco
     */
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Mínimo 8 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "Senha deve ter maiúscula, minúscula, número e caractere especial")
    @Transient
    private String senhaPlana;

    @Size(max = 20)
    private String telefone;

    @Size(max = 255)
    private String endereco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.CUSTOMER;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    public enum Role {
        CUSTOMER("Cliente"),
        ADMIN("Administrador");

        private final String descricao;

        Role(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }
}

