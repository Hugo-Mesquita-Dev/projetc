package com.java.projetc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    private static final String EMAIL_LOJA = "hugomesquitadev@gmail.com";

    public void enviarContatoLoja(String nome, String email, String telefone, String assunto, String mensagem) {
        if (mailSender == null) {
            // Modo desenvolvimento: apenas logar
            logarContato(nome, email, telefone, assunto, mensagem);
            return;
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email);
            message.setTo(EMAIL_LOJA);
            message.setSubject("[Love Makeup BL] Novo Contato: " + assunto);
            message.setText(construirCorpoEmail(nome, email, telefone, assunto, mensagem));

            mailSender.send(message);
            System.out.println("✅ Email enviado com sucesso para: " + EMAIL_LOJA);
        } catch (Exception e) {
            System.out.println("❌ Erro ao enviar email: " + e.getMessage());
            logarContato(nome, email, telefone, assunto, mensagem);
        }
    }

    private String construirCorpoEmail(String nome, String email, String telefone, String assunto, String mensagem) {
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return "=== NOVO CONTATO - LOVE MAKEUP BL ===\n\n" +
                "Data/Hora: " + agora.format(formatter) + "\n" +
                "Nome: " + nome + "\n" +
                "Email: " + email + "\n" +
                "Telefone: " + (telefone.isEmpty() ? "Não informado" : telefone) + "\n" +
                "Assunto: " + assunto + "\n\n" +
                "=== MENSAGEM ===\n" +
                mensagem + "\n\n" +
                "=== FIM DA MENSAGEM ===\n";
    }

    private void logarContato(String nome, String email, String telefone, String assunto, String mensagem) {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   NOVO CONTATO - LOVE MAKEUP BL        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("📝 Nome: " + nome);
        System.out.println("📧 Email: " + email);
        System.out.println("📱 Telefone: " + (telefone.isEmpty() ? "Não informado" : telefone));
        System.out.println("📌 Assunto: " + assunto);
        System.out.println("💬 Mensagem: " + mensagem);
        System.out.println("📬 Será enviado para: " + EMAIL_LOJA);
        System.out.println("╔════════════════════════════════════════╗\n");
    }
}

