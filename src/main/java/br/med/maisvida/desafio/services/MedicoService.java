package br.med.maisvida.desafio.services;

import br.med.maisvida.desafio.domain.Medico;

import java.util.List;

public interface MedicoService {

    Medico find(String id);
    List<Medico> findAll();
    Medico add(Medico m);
    Medico update(Medico m);
    void delete(String id);
}
