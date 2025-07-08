package br.edu.ifce.meuprimeirospringboot.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.corneli.matriculese.beans.User;
import br.com.corneli.matriculese.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository user;

	@Override

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User u = user.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		System.out.println("Usuário Logado: " + u.getNome());
		
		Set<GrantedAuthority> authorities = u.getRoles().stream()
				.map(role -> (GrantedAuthority) () -> "ROLE_" +  role.getName().name()).collect(Collectors.toSet());
		
		System.out.println(authorities);

		return org.springframework.security.core.userdetails.User.builder()
				.username(u.getEmail())
				.password(u.getPassword())
				.authorities(authorities)
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();

	}

}
