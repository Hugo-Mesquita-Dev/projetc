package com.java.projetc.service;

import com.java.projetc.model.AuditLog;
import com.java.projetc.repository.AuditLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * AuditService - Implementa A09: Security Logging & Monitoring
 *
 * Serviço para registrar eventos de segurança
 * NUNCA registra dados sensíveis (senhas, tokens, etc)
 */
@Service
public class AuditService {

    private static final Logger logger = LoggerFactory.getLogger(AuditService.class);
    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    /**
     * Registrar evento de auditoria
     *
     * @param usuarioId ID do usuário (ou 0 se anônimo)
     * @param tipoEvento tipo do evento
     * @param descricao descrição do evento
     * @param enderecIp IP do cliente
     * @param userAgent user agent do cliente
     * @param resultado resultado (SUCESSO, FALHA, BLOQUEADO)
     */
    public void registrarEvento(Long usuarioId, String tipoEvento, String descricao,
                               String enderecIp, String userAgent, String resultado) {
        try {
            AuditLog log = AuditLog.builder()
                .usuarioId(usuarioId != null ? usuarioId : 0L)
                .tipoEvento(tipoEvento)
                .descricao(descricao)
                .enderecIp(enderecIp)
                .userAgent(userAgent)
                .resultado(resultado)
                .build();

            auditLogRepository.save(log);

            // Log também em arquivo (para backup)
            logger.info("AUDIT - Tipo: {}, Usuário: {}, Resultado: {}, IP: {}",
                tipoEvento, usuarioId, resultado, enderecIp);

        } catch (Exception e) {
            // NUNCA falhar por causa de log
            logger.error("Erro ao registrar auditoria", e);
        }
    }

    /**
     * Registrar login bem-sucedido
     */
    public void registrarLogin(Long usuarioId, String enderecIp, String userAgent) {
        registrarEvento(usuarioId, AuditLog.TipoEvento.LOGIN.name(),
            "Login bem-sucedido", enderecIp, userAgent, AuditLog.Resultado.SUCESSO.name());
    }

    /**
     * Registrar tentativa de login falhada
     */
    public void registrarLoginFalhado(String email, String enderecIp, String userAgent) {
        registrarEvento(0L, AuditLog.TipoEvento.TENTATIVA_LOGIN_FALHA.name(),
            "Tentativa de login com email: " + email, enderecIp, userAgent, AuditLog.Resultado.FALHA.name());
    }

    /**
     * Registrar logout
     */
    public void registrarLogout(Long usuarioId, String enderecIp, String userAgent) {
        registrarEvento(usuarioId, AuditLog.TipoEvento.LOGOUT.name(),
            "Logout", enderecIp, userAgent, AuditLog.Resultado.SUCESSO.name());
    }

    /**
     * Registrar cadastro
     */
    public void registrarCadastro(Long usuarioId, String email, String enderecIp, String userAgent) {
        registrarEvento(usuarioId, AuditLog.TipoEvento.CADASTRO.name(),
            "Cadastro com email: " + email, enderecIp, userAgent, AuditLog.Resultado.SUCESSO.name());
    }

    /**
     * Registrar alteração de perfil
     */
    public void registrarUpdatePerfil(Long usuarioId, String enderecIp, String userAgent) {
        registrarEvento(usuarioId, AuditLog.TipoEvento.UPDATE_PERFIL.name(),
            "Perfil atualizado", enderecIp, userAgent, AuditLog.Resultado.SUCESSO.name());
    }

    /**
     * Registrar acesso negado
     */
    public void registrarAcessoNegado(Long usuarioId, String descricao, String enderecIp, String userAgent) {
        registrarEvento(usuarioId, AuditLog.TipoEvento.ACESSO_NEGADO.name(),
            descricao, enderecIp, userAgent, AuditLog.Resultado.BLOQUEADO.name());
    }

    /**
     * Registrar rate limit excedido
     */
    public void registrarRateLimitExcedido(Long usuarioId, String enderecIp, String userAgent) {
        registrarEvento(usuarioId, AuditLog.TipoEvento.RATE_LIMIT_EXCEDIDO.name(),
            "Rate limit excedido", enderecIp, userAgent, AuditLog.Resultado.BLOQUEADO.name());
    }

    /**
     * Buscar logs de um usuário
     */
    public List<AuditLog> obterLogsUsuario(Long usuarioId) {
        return auditLogRepository.findByUsuarioId(usuarioId);
    }

    /**
     * Buscar logs recentes (últimas 24h)
     */
    public List<AuditLog> obterLogsRecentes(Long usuarioId) {
        LocalDateTime dataInicio = LocalDateTime.now().minusHours(24);
        return auditLogRepository.findLogsRecentes(usuarioId, dataInicio);
    }

    /**
     * Buscar tentativas de login falhadas (últimas 24h)
     */
    public List<AuditLog> obterTentativasLoginFalhadas() {
        LocalDateTime dataInicio = LocalDateTime.now().minusHours(24);
        return auditLogRepository.findTentativasLoginFalhadas(dataInicio);
    }

    /**
     * Contar logins recentes de um usuário
     * Usado para detectar suspeitas
     */
    public long contarLoginsRecentes(Long usuarioId) {
        LocalDateTime dataInicio = LocalDateTime.now().minusHours(1);
        return auditLogRepository.contarLoginsRecentes(usuarioId, dataInicio);
    }
}

