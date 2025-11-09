package com.estudo.hexagonal.application.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Cliente {

    public Cliente() {
        isCpfValido = false;
    }

    private String id;
    private String nome;
    private Endereco endereco;
    private String cpf;
    private Boolean isCpfValido;
}
