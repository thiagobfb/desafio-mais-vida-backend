
package br.med.maisvida.desafio.config;

import br.med.maisvida.desafio.domain.Medico;
import br.med.maisvida.desafio.domain.Perfil;
import br.med.maisvida.desafio.domain.Usuario;
import br.med.maisvida.desafio.domain.enums.StatusMedicoEnum;
import br.med.maisvida.desafio.repositories.MedicoRepository;
import br.med.maisvida.desafio.repositories.PerfilRepository;
import br.med.maisvida.desafio.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Carga implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PerfilRepository perfilRepository;

	@Autowired
	MedicoRepository medicoRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent e) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();

		List<Perfil> perfis = perfilRepository.findAll();

		if (perfis.isEmpty()) {
			perfilRepository.save(new Perfil("ROLE_ADMIN"));
			perfilRepository.save(new Perfil("ROLE_USUARIO"));

			Perfil perfil = perfilRepository.findByNome("ROLE_ADMIN");

			List<Perfil> novosPerfis = new ArrayList<>();

			novosPerfis.add(perfil);

			usuarioRepository.save(new Usuario("ADMIN", "admin", encoder.encode("123"), Boolean.TRUE, novosPerfis));

		}

		medicoRepository.save(new Medico("1", "João", "José", "Clínico Geral", "joao.jose@mail.com", Boolean.TRUE,
				StatusMedicoEnum.DISPONIVEL, "DF", "Brasília"));
	}

}
