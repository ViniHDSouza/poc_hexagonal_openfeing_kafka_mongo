package com.estudo.hexagonal.adapters.out.repository.mapper;

import com.estudo.hexagonal.adapters.out.repository.entity.ClienteEntity;
import com.estudo.hexagonal.adapters.out.repository.entity.EnderecoEntity;
import com.estudo.hexagonal.application.core.domain.Cliente;
import com.estudo.hexagonal.application.core.domain.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteEntityMapper {
    ClienteEntity toClienteEntity(Cliente cliente);
    Cliente toCliente(ClienteEntity clienteEntity);
    Endereco toEndereco(EnderecoEntity enderecoEntity);
    EnderecoEntity toEnderecoEntity(Endereco endereco);
}
