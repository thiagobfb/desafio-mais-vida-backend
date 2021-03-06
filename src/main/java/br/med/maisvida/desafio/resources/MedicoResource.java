package br.med.maisvida.desafio.resources;

import br.med.maisvida.desafio.domain.Medico;
import br.med.maisvida.desafio.services.MedicoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/medicos")
@Api(value = "/api/v1.0.0", description = "API's de manutenção de dados de médico")
public class MedicoResource {

    @Autowired
    private MedicoService medicoService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Lista os médicos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta efetuada com sucesso"),
            @ApiResponse(code = 400, message = "Erro no servico"),
            @ApiResponse(code = 404, message = "Nenhum registro encontrado") })
    public ResponseEntity<?> getMedicos() {
        List<Medico> medicos = medicoService.findAll();
        return ResponseEntity.ok().body(medicos);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Recupera o médico cadastrado pelo Id.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Identificador único do médico que será consultado.",
                    required = true, dataType = "integer", paramType = "path")})
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Consulta efetuada com sucesso.") })
    public ResponseEntity<?> getMedico(@PathVariable("id") String id) {
        Medico medico = medicoService.find(id);
        return ResponseEntity.ok().body(medico);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Incluir médico", notes = "Realiza o cadastramento de uma novo médico.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operação efetuada com sucesso. ") })
    public ResponseEntity<?> incluirMedico(Medico medico) {
        Medico medicoRetorno = medicoService.add(medico);
        return ResponseEntity.ok().body(medicoRetorno);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Alterar médico", notes = "Altera os dados de um médico previamente"
            + " cadastrado.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Identificador único do médico que será alterado.",
                    required = true, dataType = "integer", paramType = "path")})
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Alteração efetuada com sucesso.") })
    public ResponseEntity<?> atualizarMedico(@PathVariable("id") String id, Medico medico) {
        medico.setId(id);
        Medico medicoRetorno = medicoService.update(medico);
        return ResponseEntity.ok().body(medicoRetorno);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Excluir médico", notes = "Efetua a exclusão de um determinado médico.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Identificador único do médico que será excluído.",
                    required = true, dataType = "integer", paramType = "path") })
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Exclusão efetuada com sucesso") })
    public ResponseEntity<?> deleteMedico(@PathVariable("id") String id) {
        Medico medico = medicoService.find(id);
        if (medico == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        medicoService.delete(medico.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
