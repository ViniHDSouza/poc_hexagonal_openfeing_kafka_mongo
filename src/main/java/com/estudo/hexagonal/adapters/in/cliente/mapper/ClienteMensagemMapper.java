package com.estudo.hexagonal.adapters.in.cliente.mapper;

import com.estudo.hexagonal.adapters.in.cliente.mensagem.ClienteMessagem;
import com.estudo.hexagonal.application.core.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMensagemMapper {
    @Mapping(target = "endereco" , ignore = true)
    Cliente toCliente(ClienteMessagem clienteMessagem);
}
