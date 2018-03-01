package br.med.maisvida.desafiomaisvidabackend.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum StatusMedicoEnum {
    OCUPADO(1, "Ocupado"),
    DISPONIVEL(2, "Disponível");

    @Setter
    private Integer codigo;
    @Setter
    private String descricao;
}
