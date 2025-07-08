package br.edu.ifce.meuprimeirospringboot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.ifce.meuprimeirospringboot.beans.Endereco;
import br.edu.ifce.meuprimeirospringboot.beans.Telefone;
import br.edu.ifce.meuprimeirospringboot.beans.Usuario;
import br.edu.ifce.meuprimeirospringboot.enums.Raca;
import br.edu.ifce.meuprimeirospringboot.model.Role;
import br.edu.ifce.meuprimeirospringboot.model.RoleName;
import br.edu.ifce.meuprimeirospringboot.repository.RoleRepository;
import br.edu.ifce.meuprimeirospringboot.repository.UsuarioRepository;

@SpringBootApplication
public class MeuprimeirospringbootApplication implements CommandLineRunner {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(MeuprimeirospringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Usuario u = new Usuario();
		// u.setCpf("00000000000");
		// u.setNome("Fulano de Tal");
		// u.setEmail("fulano@gmail.com");
		// u.setRaca(Raca.Indígena);
		// u.setDtNascimento(new Date());

		// Endereco e = new Endereco();
		// e.setBairro("Jereissati");
		// e.setCep("60000-000");
		// e.setLogradouro("Rua I");
		// e.setNumero("777");

		// List<Telefone> l = new ArrayList<Telefone>();
		// Telefone t1 = new Telefone();
		// t1.setNumero("9999-9999");
		// t1.setIsPrincipal(true);
		// t1.setIsWpp(true);
		// l.add(t1);

		// u.setEndereco(e);
		// u.setTelefones(l);

		// usuarioRepository.save(u);

		// Long n = usuarioRepository.count();
		// System.out.println(n);

		// =====================
		// USUÁRIO 1 - Fulano de Tal
		// =====================
		if (usuarioRepository.findByEmail("fulano@gmail.com").isEmpty()) {
			Usuario u = new Usuario();
			u.setCpf("00000000000");
			u.setNome("Fulano de Tal");
			u.setEmail("fulano@gmail.com");
			u.setSenha(encoder.encode("senha123"));
			u.setRaca(Raca.Indígena);
			u.setDtNascimento(new Date());

			Endereco e = new Endereco();
			e.setBairro("Jereissati");
			e.setCep("60000-000");
			e.setLogradouro("Rua I");
			e.setNumero("777");

			Telefone t1 = new Telefone();
			t1.setNumero("9999-9999");
			t1.setIsPrincipal(true);
			t1.setIsWpp(true);

			u.setEndereco(e);
			u.setTelefones(List.of(t1));
			u.setRoles(List.of()); // Nenhuma role

			usuarioRepository.save(u);
			System.out.println("✅ Usuário Fulano de Tal criado.");
		}

		// =====================
		// USUÁRIO 2 - ADMINISTRADOR
		// =====================
		if (usuarioRepository.findByEmail("admin@ifce.edu.br").isEmpty()) {

			// Cria role ADMIN se ainda não existir
			Role adminRole = roleRepository.findByName(RoleName.ADMIN)
					.orElseGet(() -> roleRepository.save(new Role(RoleName.ADMIN)));

			Usuario admin = new Usuario();
			admin.setNome("Administrador");
			admin.setEmail("admin@ifce.edu.br");
			admin.setCpf("12345678900");
			admin.setSenha(encoder.encode("123456"));
			admin.setDtNascimento(new Date());
			admin.setRaca(Raca.Branco);
			admin.setRoles(List.of(adminRole));

			usuarioRepository.save(admin);
			System.out.println("✅ Usuário ADMIN criado.");
		}

	}

}
