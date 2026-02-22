package com.java.projetc.repository;

import com.java.projetc.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * AuditLogRepository - Acesso aos logs de auditoria
 */
@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    /**
     * Buscar logs de um usuário
     */
    List<AuditLog> findByUsuarioId(Long usuarioId);

    /**
     * Buscar logs por tipo de evento
     */
    List<AuditLog> findByTipoEvento(String tipoEvento);

    /**
     * Buscar logs recentes de um usuário (últimas 24 horas)
     */
    @Query("SELECT a FROM AuditLog a WHERE a.usuarioId = :usuarioId AND a.dataCriacao >= :dataInicio ORDER BY a.dataCriacao DESC")
    List<AuditLog> findLogsRecentes(@Param("usuarioId") Long usuarioId, @Param("dataInicio") LocalDateTime dataInicio);

    /**
     * Buscar tentativas falhadas de login
     */
    @Query("SELECT a FROM AuditLog a WHERE a.tipoEvento = 'TENTATIVA_LOGIN_FALHA' AND a.dataCriacao >= :dataInicio ORDER BY a.dataCriacao DESC")
    List<AuditLog> findTentativasLoginFalhadas(@Param("dataInicio") LocalDateTime dataInicio);

    /**
     * Buscar acessos negados
     */
    @Query("SELECT a FROM AuditLog a WHERE a.tipoEvento = 'ACESSO_NEGADO' AND a.dataCriacao >= :dataInicio ORDER BY a.dataCriacao DESC")
    List<AuditLog> findAcessosNegados(@Param("dataInicio") LocalDateTime dataInicio);

    /**
     * Contar logins de um usuário nas últimas 24h
     */
    @Query("SELECT COUNT(a) FROM AuditLog a WHERE a.usuarioId = :usuarioId AND a.tipoEvento = 'LOGIN' AND a.dataCriacao >= :dataInicio")
    long contarLoginsRecentes(@Param("usuarioId") Long usuarioId, @Param("dataInicio") LocalDateTime dataInicio);
}

