package br.med.maisvida.desafio.repositories;

import br.med.maisvida.desafio.domain.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Usuario findByUsername(String username);
}
