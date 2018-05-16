package br.med.maisvida.desafio.domain;

import br.med.maisvida.desafio.domain.enums.StatusMedicoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(exclude = {"primeiroNome","ultimoNome",
        "especialidade","email","ativo","status","estado","cidade"})
public class Medico implements Serializable {

    private static final long serialVersionUID = 7948880953266581757L;

    @Id
    private String id;

    private String primeiroNome;
    private String ultimoNome;
    private String especialidade;
    private String email;
    private Boolean ativo;
    private StatusMedicoEnum status;
    private String estado;
    private String cidade;


}
