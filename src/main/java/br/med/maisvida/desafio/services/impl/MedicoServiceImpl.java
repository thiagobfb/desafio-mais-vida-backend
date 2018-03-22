package br.med.maisvida.desafio.services.impl;

import br.med.maisvida.desafio.domain.Medico;
import br.med.maisvida.desafio.repositories.MedicoRepository;
import br.med.maisvida.desafio.services.MedicoService;
import br.med.maisvida.desafio.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private NextSequenceService nextSequenceService;

    @Override
    public Medico find(Long id) {
        Optional<Medico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrato ! Id: " + id + ", Tipo: " + Medico.class.getName()));
    }

    @Override
    public List<Medico> findAll() {
        return repository.findAll();
    }

    @Override
    public Medico add(Medico m) {
        Long id = new Long(nextSequenceService.getNextSequence("customSequences"));
        m.setId(id);
        return repository.save(m);
    }

    @Override
    public Medico update(Medico m) {
        Optional<Medico> obj = repository.findById(m.getId());
        Medico medico = obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrato ! Id: " + m.getId() + ", Tipo: " + Medico.class.getName()));
        medico.setId(m.getId());
        medico.setAtivo(m.getAtivo());
        medico.setCidade(m.getCidade());
        medico.setEmail(m.getEmail());
        medico.setEspecialidade(m.getEspecialidade());
        medico.setEstado(m.getEstado());
        medico.setStatus(m.getStatus());
        medico.setPrimeiroNome(m.getPrimeiroNome());
        medico.setUltimoNome(m.getUltimoNome());
        return repository.save(medico);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ObjectNotFoundException(
                    "Objeto não encontrato ! Id: " + id + ", Tipo: " + Medico.class.getName());
        }
        repository.deleteById(id);
    }
}
