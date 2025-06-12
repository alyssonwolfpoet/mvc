package br.edu.ifce.meuprimeirospringboot.service;


import br.edu.ifce.meuprimeirospringboot.beans.Usuario;
public interface UsuarioService {
	Usuario buscarPorCPF(String cpf);
	Usuario salvar(Usuario usuario);
	Usuario editar(Long id, Usuario usuarioAtualizado);
}
