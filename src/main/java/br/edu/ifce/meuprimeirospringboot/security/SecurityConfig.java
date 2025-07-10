package br.edu.ifce.meuprimeirospringboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import br.edu.ifce.meuprimeirospringboot.repository.UsuarioRepository;

@Configuration
public class SecurityConfig {

    @Autowired
    private final CustomSuccessHandler customSuccessHandler;

    public SecurityConfig(CustomSuccessHandler customSuccessHandler) {
        this.customSuccessHandler = customSuccessHandler;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/webjars/**", "/css/**", "/js/**", "/image/**", "/uploads/**",
                                "/static/**")
                        .permitAll()
                        .requestMatchers("/aluno/cadastrar").permitAll()
                        .requestMatchers("/aluno/confirmar-cadastro").permitAll()
                        .requestMatchers("/confirmar-email").permitAll()
                        .requestMatchers("/login", "/hello").permitAll()

                        //
                        .requestMatchers("/api/inscricoes/inscrever").permitAll()
                        //
                        .requestMatchers("/aluno/listar").hasRole("ADMIN")
                        .requestMatchers("/curso/cadastrar").hasRole("ADMIN")
                        .requestMatchers("/admin/dashboard").hasRole("ADMIN")
                        .requestMatchers("/polo/cadastrar").hasRole("ADMIN")

                        .anyRequest().authenticated())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .formLogin(form -> form
                        .loginPage("/login")
                        // .defaultSuccessUrl("/admin/dashboard", true)
                        .successHandler(customSuccessHandler)
                        .permitAll())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(
                                org.springframework.security.config.http.SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(1)
                        .expiredUrl("/login?expired"))
                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(UsuarioRepository userRepository) {
        return new CustomUserDetailsService();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
