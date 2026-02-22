package com.java.projetc.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

/**
 * AuditLog - Implementa A09: Security Logging & Monitoring
 *
 * Registra eventos de segurança:
 * - Login/Logout
 * - Alteração de dados
 * - Tentativas falhadas
 * - Acesso negado
 */
@Entity
@Table(name = "audit_logs", indexes = {
    @Index(name = "idx_usuario_id", columnList = "usuario_id"),
    @Index(name = "idx_data_criacao", columnList = "data_criacao"),
    @Index(name = "idx_tipo_evento", columnList = "tipo_evento")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private String tipoEvento; // LOGIN, LOGOUT, CADASTRO, UPDATE, DELETE, ACESSO_NEGADO, etc

    @Column(length = 500)
    private String descricao;

    @Column(length = 50)
    private String enderecIp; // IP do cliente

    @Column(length = 255)
    private String userAgent; // Browser/Client info

    @Column(length = 100)
    private String resultado; // SUCESSO, FALHA, etc

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataCriacao;

    public enum TipoEvento {
        LOGIN("Login"),
        LOGOUT("Logout"),
        CADASTRO("Cadastro"),
        UPDATE_PERFIL("Atualização de Perfil"),
        UPDATE_ENDERECO("Atualização de Endereço"),
        ALTERACAO_SENHA("Alteração de Senha"),
        COMPRA_REALIZADA("Compra Realizada"),
        ACESSO_NEGADO("Acesso Negado"),
        TENTATIVA_LOGIN_FALHA("Tentativa de Login Falhada"),
        RATE_LIMIT_EXCEDIDO("Rate Limit Excedido"),
        SUSPEITA_SEGURANCA("Suspeita de Segurança");

        private final String descricao;

        TipoEvento(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    public enum Resultado {
        SUCESSO("Sucesso"),
        FALHA("Falha"),
        BLOQUEADO("Bloqueado");

        private final String valor;

        Resultado(String valor) {
            this.valor = valor;
        }

        public String getValor() {
            return valor;
        }
    }
}

