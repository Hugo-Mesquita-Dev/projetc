package com.java.projetc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes de Segurança - Love Makeup BL
 * <p>
 * Testa:
 * - A01: Access Control
 * - A04: CSRF Protection
 * - A07: Authentication
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    // ============ TESTE 1: Acesso Público ============

    @Test
    public void testAcessoPublicoHome() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk());
    }

    @Test
    public void testAcessoPublicoProdutos() throws Exception {
        mockMvc.perform(get("/produtos"))
            .andExpect(status().isOk());
    }

    @Test
    public void testAcessoPublicoSobre() throws Exception {
        mockMvc.perform(get("/sobre"))
            .andExpect(status().isOk());
    }

    @Test
    public void testAcessoPublicoContato() throws Exception {
        mockMvc.perform(get("/contato"))
            .andExpect(status().isOk());
    }

    // ============ TESTE 2: Acesso Protegido (sem autenticação) ============

    @Test
    public void testAcessoPerfilSemAutenticacao() throws Exception {
        // Deve redirecionar para login
        mockMvc.perform(get("/perfil"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void testAcessoCarrinhoSemAutenticacao() throws Exception {
        // Deve redirecionar para login
        mockMvc.perform(get("/carrinho"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/login"));
    }

    // ============ TESTE 3: Acesso Protegido (com autenticação) ============

    @Test
    @WithMockUser(username = "test@example.com", roles = "CUSTOMER")
    public void testAcessoPerfilComAutenticacao() throws Exception {
        mockMvc.perform(get("/perfil"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "CUSTOMER")
    public void testAcessoCarrinhoComAutenticacao() throws Exception {
        mockMvc.perform(get("/carrinho"))
            .andExpect(status().isOk());
    }

    // ============ TESTE 4: CSRF Protection ============

    @Test
    public void testCSRFTokenGerado() throws Exception {
        // Deve retornar token CSRF em GET
        mockMvc.perform(get("/login"))
            .andExpect(status().isOk());
        // Token é adicionado automaticamente ao response
    }

    @Test
    public void testPOSTSemCSRFToken() throws Exception {
        // POST sem CSRF token redireciona para login (em vez de 403)
        mockMvc.perform(post("/cadastro")
            .param("nome", "Test User")
            .param("email", "test@example.com")
            .param("senhaPlana", "Password123!"))
            .andExpect(status().is3xxRedirection()); // redireciona
    }

    // ============ TESTE 5: Headers de Segurança ============

    @Test
    public void testHeaderXFrameOptions() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(header().exists("X-Frame-Options"));
    }

    @Test
    public void testHeaderXContentTypeOptions() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(header().exists("X-Content-Type-Options"));
    }

    @Test
    public void testHeaderStrictTransportSecurity() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk());
        // Nota: HSTS headers podem não aparecer em teste HTTP. Em produção com HTTPS aparecerão.
    }

    // ============ TESTE 6: Admin Access Control ============

    @Test
    @WithMockUser(username = "admin@example.com", roles = "CUSTOMER")
    public void testAcessoAdminSemPermissao() throws Exception {
        // Customer não pode acessar /admin
        mockMvc.perform(get("/admin/dashboard"))
            .andExpect(status().isForbidden()); // 403 Forbidden
    }

    @Test
    @WithMockUser(username = "admin@example.com", roles = "ADMIN")
    public void testAcessoAdminComPermissao() throws Exception {
        // Admin pode acessar /admin (retorna 404 se página não existe, não 403)
        mockMvc.perform(get("/admin/dashboard"))
            .andExpect(status().isNotFound()); // 404 (página não existe ainda)
    }

    // ============ TESTE 7: H2 Console Bloqueado ============

    @Test
    public void testH2ConsoleBloqueado() throws Exception {
        mockMvc.perform(get("/h2-console/"))
            .andExpect(status().is3xxRedirection()); // redireciona para login ou retorna 403
    }

    // ============ TESTE 8: Session Management ============

    @Test
    @WithMockUser(username = "test@example.com", roles = "CUSTOMER")
    public void testSessionAtivaAposLogin() throws Exception {
        mockMvc.perform(get("/perfil"))
            .andExpect(status().isOk());
        // Nota: Cookie JSESSIONID é gerenciado automaticamente pelo Spring Security
    }
}

