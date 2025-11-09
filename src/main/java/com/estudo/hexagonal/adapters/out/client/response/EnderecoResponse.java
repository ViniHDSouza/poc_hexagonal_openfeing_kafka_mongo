package com.estudo.hexagonal.adapters.out.client.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponse {
    private String cep;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
}
