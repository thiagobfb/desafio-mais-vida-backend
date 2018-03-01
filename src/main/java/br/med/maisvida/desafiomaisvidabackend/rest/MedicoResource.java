package br.med.maisvida.desafiomaisvidabackend.rest;

import br.med.maisvida.desafiomaisvidabackend.model.Medico;
import br.med.maisvida.desafiomaisvidabackend.model.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/medicos")
public class MedicoResource {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Medico>> getMedicos() {
        return new ResponseEntity<>(medicoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> getMedico(@PathVariable("id") Long id) {
        return new ResponseEntity<>(medicoRepository.findOne(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> incluirMedico(Medico medico) {
        return new ResponseEntity<>(medicoRepository.save(medico), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> atualizarMedico(@PathVariable("id") Long id, Medico medico) {
        medico.setId(id);
        return new ResponseEntity<>(medicoRepository.save(medico), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteMedico(@PathVariable("id") Long id) {
        Medico medico = medicoRepository.findOne(id);
        medicoRepository.delete(medico);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
