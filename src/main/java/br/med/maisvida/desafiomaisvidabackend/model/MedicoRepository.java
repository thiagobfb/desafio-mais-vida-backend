package br.med.maisvida.desafiomaisvidabackend.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicoRepository extends MongoRepository<Medico, Long> {
}
