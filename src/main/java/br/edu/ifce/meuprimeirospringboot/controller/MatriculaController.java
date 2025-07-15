package br.edu.ifce.meuprimeirospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifce.meuprimeirospringboot.beans.Matricula;
import br.edu.ifce.meuprimeirospringboot.repository.TurmaRepository;
import br.edu.ifce.meuprimeirospringboot.repository.UsuarioRepository;
import br.edu.ifce.meuprimeirospringboot.service.MatriculaService;

@Controller
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaService service;

    @Autowired
    private UsuarioRepository usuarioRepository; // para os alunos

    @Autowired
    private TurmaRepository turmaRepository; // para as turmas

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("matriculas", service.listar());
        return "matricula/lista";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("matricula", new Matricula());
        model.addAttribute("alunos", usuarioRepository.findAll());
        model.addAttribute("turmas", turmaRepository.findAll());
        return "matricula/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Matricula m) {
        service.salvar(m);
        return "redirect:/matriculas/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("matricula", service.buscar(id));
        model.addAttribute("alunos", usuarioRepository.findAll());
        model.addAttribute("turmas", turmaRepository.findAll());
        return "matricula/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/matriculas/lista";
    }
}
