package com.java.projetc.controller;

import com.java.projetc.model.Usuario;
import com.java.projetc.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

/**
 * AuthController
 * Controlador para autenticação (login, cadastro, logout)
 *
 * Rotas públicas (sem autenticação):
 * - GET /login
 * - GET /cadastro
 * - POST /cadastro
 * - POST /logout
 */
@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Página de login
     * GET /login
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Página de cadastro (formulário)
     * GET /cadastro
     */
    @GetMapping("/cadastro")
    public String cadastroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    /**
     * Processar cadastro
     * POST /cadastro
     *
     * @Valid valida os campos do Usuario (anotações Jakarta Validation)
     */
    @PostMapping("/cadastro")
    public String cadastro(
            @Valid @ModelAttribute("usuario") Usuario usuario,
            RedirectAttributes redirectAttrs) {

        try {
            usuarioService.cadastrar(usuario);
            redirectAttrs.addFlashAttribute("sucesso", "Cadastro realizado com sucesso! Faça login.");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("erro", e.getMessage());
            return "redirect:/cadastro";
        }
    }

    /**
     * Logout
     * POST /logout
     *
     * Nota: Spring Security configura /logout automaticamente
     * Este método é apenas para referência
     * O logout real é tratado em SecurityConfig
     */
    @PostMapping("/logout")
    public String logout() {
        // Spring Security trata o logout automaticamente
        return "redirect:/";
    }

    /**
     * Página de erro (403 - Acesso Negado)
     * GET /error/403
     */
    @GetMapping("/error/403")
    public String acesoDenegado() {
        return "error/403";
    }
}

