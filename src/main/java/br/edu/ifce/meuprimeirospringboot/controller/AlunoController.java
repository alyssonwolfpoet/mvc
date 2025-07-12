// ✅ 1. AlunoController.java (dashboard)
package br.edu.ifce.meuprimeirospringboot.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    @GetMapping("/dashboard")
    public String dashboardAluno(Model model, Principal principal) {
        model.addAttribute("nomeUsuario", principal.getName());
        return "aluno/dashboard";
    }
}
