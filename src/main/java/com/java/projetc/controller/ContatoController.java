package com.java.projetc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.java.projetc.service.EmailService;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ContatoController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/contato")
    public Map<String, Object> enviarContato(@RequestBody Map<String, String> contato) {
        String nome = contato.get("nome");
        String email = contato.get("email");
        String telefone = contato.getOrDefault("telefone", "");
        String assunto = contato.get("assunto");
        String mensagem = contato.get("mensagem");

        try {
            // Enviar email
            emailService.enviarContatoLoja(nome, email, telefone, assunto, mensagem);

            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", true);
            response.put("mensagem", "Mensagem recebida com sucesso! Entraremos em contato em breve.");
            return response;
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("sucesso", false);
            response.put("mensagem", "Erro ao enviar mensagem. Tente novamente!");
            return response;
        }
    }
}



