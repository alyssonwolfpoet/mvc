package br.edu.ifce.meuprimeirospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.meuprimeirospringboot.beans.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
