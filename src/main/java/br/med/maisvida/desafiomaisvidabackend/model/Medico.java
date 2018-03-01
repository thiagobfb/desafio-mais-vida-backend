package br.med.maisvida.desafiomaisvidabackend.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Medico {

    @Id
    private Long id;

    private String primeiroNome;
    private String ultimoNome;
    private String especialidade;
    private String email;
    private Boolean ativo;
    private StatusMedicoEnum status;
    private String estado;
    private String cidade;
}
