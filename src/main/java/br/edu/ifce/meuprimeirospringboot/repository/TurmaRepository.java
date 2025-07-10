package br.edu.ifce.meuprimeirospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifce.meuprimeirospringboot.beans.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {}