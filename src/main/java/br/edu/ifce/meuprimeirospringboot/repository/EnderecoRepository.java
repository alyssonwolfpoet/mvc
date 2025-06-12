package br.edu.ifce.meuprimeirospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.meuprimeirospringboot.beans.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
