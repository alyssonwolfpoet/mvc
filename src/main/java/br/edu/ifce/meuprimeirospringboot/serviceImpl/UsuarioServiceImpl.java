package br.edu.ifce.meuprimeirospringboot.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifce.meuprimeirospringboot.beans.Usuario;
import br.edu.ifce.meuprimeirospringboot.exceptions.UsuarioNaoEncontradoException;
import br.edu.ifce.meuprimeirospringboot.repository.UsuarioRepository;
import br.edu.ifce.meuprimeirospringboot.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario buscarPorCPF(String cpf) {
		return usuarioRepository.findByCpf(cpf)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(cpf));
	}

	@Override
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario editar(Long id, Usuario usuarioAtualizado) {
		Usuario existente = usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));

		existente.setNome(usuarioAtualizado.getNome());
		existente.setEmail(usuarioAtualizado.getEmail());
		existente.setCpf(usuarioAtualizado.getCpf());
		existente.setDtNascimento(usuarioAtualizado.getDtNascimento());
		existente.setRaca(usuarioAtualizado.getRaca());
		existente.setEndereco(usuarioAtualizado.getEndereco());
		existente.setTelefones(usuarioAtualizado.getTelefones());
		existente.setDisciplinas(usuarioAtualizado.getDisciplinas());

		return usuarioRepository.save(existente);
	}

	@Override
	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}

	@Override
	public void deletar(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNaoEncontradoException("ID: " + id));
	}

}
