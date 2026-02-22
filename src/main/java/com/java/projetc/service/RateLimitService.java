package com.java.projetc.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RateLimitService - Implementa A05: Insecure Design
 *
 * Controla o número de requisições por usuário/IP
 * Protege contra:
 * - Brute force attacks
 * - DDoS
 * - Spam
 */
@Service
public class RateLimitService {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    /**
     * Limites de rate limiting por tipo
     */
    public static final class Limits {
        public static final int LOGIN_ATTEMPTS = 5;           // 5 tentativas por minuto
        public static final int CONTACT_REQUESTS = 10;         // 10 requisições por minuto
        public static final int API_CALLS = 100;               // 100 requisições por minuto
        public static final int REGISTRATION = 3;             // 3 cadastros por minuto
    }

    /**
     * Resolver bucket para uma chave (IP, email, etc)
     */
    public Bucket resolveBucket(String chave) {
        return buckets.computeIfAbsent(chave, k -> criarBucketPadrao());
    }

    /**
     * Resolver bucket com limite customizado
     */
    public Bucket resolveBucketComLimite(String chave, int limite, Duration duracao) {
        return buckets.computeIfAbsent(chave, k -> criarBucketComLimite(limite, duracao));
    }

    /**
     * Criar bucket padrão (100 req/min)
     */
    private Bucket criarBucketPadrao() {
        Bandwidth limite = Bandwidth.classic(Limits.API_CALLS,
            Refill.intervally(Limits.API_CALLS, Duration.ofMinutes(1)));
        return Bucket4j.builder().addLimit(limite).build();
    }

    /**
     * Criar bucket com limite customizado
     */
    private Bucket criarBucketComLimite(int limite, Duration duracao) {
        Bandwidth limiteBandwidth = Bandwidth.classic(limite,
            Refill.intervally(limite, duracao));
        return Bucket4j.builder().addLimit(limiteBandwidth).build();
    }

    /**
     * Verificar se requisição é permitida
     */
    public boolean permitirRequisicao(String chave) {
        return resolveBucket(chave).tryConsume(1);
    }

    /**
     * Verificar com limite customizado
     */
    public boolean permitirRequisicao(String chave, int limite, Duration duracao) {
        return resolveBucketComLimite(chave, limite, duracao).tryConsume(1);
    }

    /**
     * Obter tokens restantes
     */
    public long obterTokensRestantes(String chave) {
        return resolveBucket(chave).getAvailableTokens();
    }

    /**
     * Limpar bucket de um usuário
     * (útil após logout ou reset)
     */
    public void limparBucket(String chave) {
        buckets.remove(chave);
    }

    /**
     * Limpar todos os buckets
     * (útil em testes ou manutenção)
     */
    public void limparTodos() {
        buckets.clear();
    }
}

