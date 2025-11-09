package com.estudo.hexagonal.adapters.out.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "enderecos")
public class EnderecoEntity {
    @Id
    private String cep;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
}
