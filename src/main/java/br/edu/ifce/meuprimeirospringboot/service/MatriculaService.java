package br.edu.ifce.meuprimeirospringboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.beans.Matricula;
import br.edu.ifce.meuprimeirospringboot.repository.MatriculaRepository;

@Service
public class MatriculaService {
    @Autowired private MatriculaRepository repo;
    public List<Matricula> listar() { return repo.findAll(); }
    public void salvar(Matricula m) { repo.save(m); }
    public Matricula buscar(Long id) { return repo.findById(id).orElse(null); }
    public void excluir(Long id) { repo.deleteById(id); }
}