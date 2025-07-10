package br.edu.ifce.meuprimeirospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifce.meuprimeirospringboot.beans.Turma;
import br.edu.ifce.meuprimeirospringboot.service.TurmaService;

@Controller
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    private TurmaService service;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("turmas", service.listar());
        return "turma/lista";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("turma", new Turma());
        return "turma/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Turma t) {
        service.salvar(t);
        return "redirect:/turmas/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("turma", service.buscar(id));
        return "turma/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/turmas/lista";
    }
}
