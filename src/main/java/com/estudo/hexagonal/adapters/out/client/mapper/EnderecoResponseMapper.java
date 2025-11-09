package com.estudo.hexagonal.adapters.out.client.mapper;


import com.estudo.hexagonal.adapters.out.client.response.EnderecoResponse;
import com.estudo.hexagonal.application.core.domain.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoResponseMapper {
    Endereco toEndereco(EnderecoResponse enderecoResponse);
}
