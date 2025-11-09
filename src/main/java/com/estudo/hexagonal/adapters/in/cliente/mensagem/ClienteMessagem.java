package com.estudo.hexagonal.adapters.in.cliente.mensagem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteMessagem {
    private String id;
    private String nome;
    private String cpf;
    private String cep;
    private Boolean isCpfValido;
}
