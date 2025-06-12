package br.edu.ifce.meuprimeirospringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import br.edu.ifce.meuprimeirospringboot.beans.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long > {

	 Optional<Usuario> findByCpf(String cpf);	
	

	

}
