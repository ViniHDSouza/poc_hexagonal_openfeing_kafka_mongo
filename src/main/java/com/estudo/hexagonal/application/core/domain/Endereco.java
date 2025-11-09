package com.estudo.hexagonal.application.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private String cep;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
}
