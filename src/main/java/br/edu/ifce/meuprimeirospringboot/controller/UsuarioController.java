package br.edu.ifce.meuprimeirospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifce.meuprimeirospringboot.beans.Usuario;
import br.edu.ifce.meuprimeirospringboot.dto.CpfDTO;
import br.edu.ifce.meuprimeirospringboot.serviceImpl.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioServiceImpl usuarioService;

	// @RequestParam --> GET /usuario?cpf=12345678900
	// @RequestBody --> GET /usuario/12345678900 @GetMapping("/usuario/{cpf}")
	// @PathVariable --> Lê o corpo da requisição (normalmente JSON) e mapeia para
	// um objeto Java.

	@PostMapping("/buscar-por-cpf")
	public ResponseEntity<Usuario> getUsuarioPorCPF(@RequestBody CpfDTO dto) {
		Usuario usuario = usuarioService.buscarPorCPF(dto.getCpf());
		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
		Usuario novoUsuario = usuarioService.salvar(usuario);
		return ResponseEntity.ok(novoUsuario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> editarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
		Usuario usuarioEditado = usuarioService.editar(id, usuarioAtualizado);
		return ResponseEntity.ok(usuarioEditado);
	}

	@GetMapping
	public List<Usuario> listarTodos() {
		return usuarioService.listarTodos();
	}

	@DeleteMapping("/{id}")
	public void excluir(@PathVariable Long id) {
		usuarioService.deletar(id);
	}

}
