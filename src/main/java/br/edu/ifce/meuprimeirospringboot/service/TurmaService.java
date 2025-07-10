package br.edu.ifce.meuprimeirospringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.beans.Turma;
import br.edu.ifce.meuprimeirospringboot.repository.TurmaRepository;

@Service
public class TurmaService {
    @Autowired private TurmaRepository repo;
    public List<Turma> listar() { return repo.findAll(); }
    public void salvar(Turma t) { repo.save(t); }
    public Turma buscar(Long id) { return repo.findById(id).orElse(null); }
    public void excluir(Long id) { repo.deleteById(id); }
}