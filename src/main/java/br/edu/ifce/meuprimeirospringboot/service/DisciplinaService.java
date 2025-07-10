package br.edu.ifce.meuprimeirospringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.beans.Disciplina;
import br.edu.ifce.meuprimeirospringboot.repository.DisciplinaRepository;

@Service
public class DisciplinaService {
    @Autowired private DisciplinaRepository repo;
    public List<Disciplina> listar() { return repo.findAll(); }
    public Disciplina buscar(Long id) { return repo.findById(id).orElse(null); }
    public void salvar(Disciplina d) { repo.save(d); }
    public void excluir(Long id) { repo.deleteById(id); }
}