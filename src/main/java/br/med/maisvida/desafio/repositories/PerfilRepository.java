package br.med.maisvida.desafio.repositories;

import br.med.maisvida.desafio.domain.Perfil;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PerfilRepository extends MongoRepository<Perfil, String> {

    Perfil findByNome(String nome);
}
