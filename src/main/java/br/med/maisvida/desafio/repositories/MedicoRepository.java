package br.med.maisvida.desafio.repositories;

import br.med.maisvida.desafio.domain.Medico;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicoRepository extends MongoRepository<Medico, Long> {
}
