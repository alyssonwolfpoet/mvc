package br.edu.ifce.meuprimeirospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifce.meuprimeirospringboot.beans.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {}
