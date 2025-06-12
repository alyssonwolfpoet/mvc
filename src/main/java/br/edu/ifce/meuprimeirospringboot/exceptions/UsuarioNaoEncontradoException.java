package br.edu.ifce.meuprimeirospringboot.exceptions;

@SuppressWarnings("serial")
public class UsuarioNaoEncontradoException extends RuntimeException {
	public UsuarioNaoEncontradoException(String cpf) {
        super("Usuário com CPF " + cpf + " não encontrado.");
    }
}
