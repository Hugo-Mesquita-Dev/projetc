package com.java.projetc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("lojaNome", "Love Makeup BL");
        return "index";
    }

    @GetMapping("/produtos")
    public String produtos(Model model) {
        model.addAttribute("lojaNome", "Love Makeup BL");
        return "produtos";
    }

    @GetMapping("/carrinho")
    public String carrinho(Model model) {
        model.addAttribute("lojaNome", "Love Makeup BL");
        return "carrinho";
    }

    @GetMapping("/contato")
    public String contato(Model model) {
        model.addAttribute("lojaNome", "Love Makeup BL");
        return "contato";
    }

    @GetMapping("/sobre")
    public String sobre(Model model) {
        model.addAttribute("lojaNome", "Love Makeup BL");
        return "sobre";
    }

    @GetMapping("/perfil")
    public String perfil(Model model) {
        model.addAttribute("lojaNome", "Love Makeup BL");
        return "perfil";
    }

}

