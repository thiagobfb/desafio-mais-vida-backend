package br.med.maisvida.desafio.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class StandardError implements Serializable {

    private static final long serialVersionUID = -8249244283943541054L;

    private Integer status;
    private String msg;
    private Long timeStamp;
}
