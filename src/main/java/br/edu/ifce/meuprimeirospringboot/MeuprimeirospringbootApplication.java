package br.edu.ifce.meuprimeirospringboot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifce.meuprimeirospringboot.beans.Endereco;
import br.edu.ifce.meuprimeirospringboot.beans.Telefone;
import br.edu.ifce.meuprimeirospringboot.beans.Usuario;
import br.edu.ifce.meuprimeirospringboot.enums.Raca;
import br.edu.ifce.meuprimeirospringboot.repository.UsuarioRepository;

@SpringBootApplication
public class MeuprimeirospringbootApplication implements CommandLineRunner  {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(MeuprimeirospringbootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Usuario u = new Usuario();
		u.setCpf("00000000000");
		u.setNome("Fulano de Tal");
		u.setEmail("fulano@gmail.com");
		u.setRaca(Raca.Indígena);
		u.setDtNascimento(new Date());
		
		Endereco e = new Endereco();
		e.setBairro("Jereissati");
		e.setCep("60000-000");
		e.setLogradouro("Rua I");
		e.setNumero("777");
		
		List<Telefone> l = new ArrayList<Telefone>();
		Telefone t1 = new Telefone();
		t1.setNumero("9999-9999");
		t1.setIsPrincipal(true);
		t1.setIsWpp(true);
		l.add(t1);
			
		u.setEndereco(e);
		u.setTelefones(l);
	
		usuarioRepository.save(u);

		
		Long n =  usuarioRepository.count();
		System.out.println(n);
		
		
		
		
	}

}
