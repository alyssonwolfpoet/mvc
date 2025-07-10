package br.edu.ifce.meuprimeirospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifce.meuprimeirospringboot.beans.Disciplina;
import br.edu.ifce.meuprimeirospringboot.service.DisciplinaService;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaService service;

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("disciplinas", service.listar());
        return "disciplina/lista";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        return "disciplina/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Disciplina d) {
        service.salvar(d);
        return "redirect:/disciplinas/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("disciplina", service.buscar(id));
        return "disciplina/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/disciplinas/lista";
    }
}