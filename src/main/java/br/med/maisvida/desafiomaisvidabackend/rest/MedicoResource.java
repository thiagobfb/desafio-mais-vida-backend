package br.med.maisvida.desafiomaisvidabackend.rest;

import br.med.maisvida.desafiomaisvidabackend.model.Medico;
import br.med.maisvida.desafiomaisvidabackend.model.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@RestController(value = "/medicos")
@Api(value = "/api/v1.0.0", description = "API's de manutenção de dados de médico")
public class MedicoResource {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Lista os médicos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta efetuada com sucesso", response = Medico.class),
            @ApiResponse(code = 400, message = "Erro no servico", response = Medico.class),
            @ApiResponse(code = 404, message = "Nenhum registro encontrado", response = Medico.class) })
    public ResponseEntity<List<Medico>> getMedicos() {
        return new ResponseEntity<>(medicoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> getMedico(@PathVariable("id") Long id) {
        return new ResponseEntity<>(medicoRepository.findOne(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Incluir médico", notes = "Realiza o cadastramento de uma novo médico.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "medico", value = "Informações do medico que será incluído.", required = true, dataType = "Medico", paramType = "body") })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação efetuada com sucesso. ", response = Medico.class) })
    public ResponseEntity<Medico> incluirMedico(Medico medico) {
        return new ResponseEntity<>(medicoRepository.save(medico), HttpStatus.OK);
    }

    @PutMapping(value = "/{id:\\d+}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Alterar médico", notes = "Altera os dados de um médico previamente"
            + " cadastrado.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Identificador único do médico que será alterado.",
                    required = true, dataType = "integer", paramType = "path"),
            @ApiImplicitParam(name = "medico", value = "Informações do médico que será alteradao.",
                    required = true, dataType = "Medico", paramType = "body") })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Alteração efetuada com sucesso.") })
    public ResponseEntity<Medico> atualizarMedico(@PathVariable("id") Long id, Medico medico) {
        medico.setId(id);
        return new ResponseEntity<>(medicoRepository.save(medico), HttpStatus.OK);
    }

    @GetMapping(value = "/{id:\\d+}")
    @ApiOperation(value = "Excluir médico", notes = "Efetua a exclusão de um determinado médico.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Identificador único do médico que será excluído.",
                    required = true, dataType = "integer", paramType = "path") })
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Exclusão efetuada com sucesso") })
    public ResponseEntity<Void> deleteMedico(@PathVariable("id") Long id) {
        Medico medico = medicoRepository.findOne(id);
        if (medico == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        medicoRepository.delete(medico);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
