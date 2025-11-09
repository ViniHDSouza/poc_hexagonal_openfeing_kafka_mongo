package com.estudo.hexagonal.adapters.in.controllers.mapper;

import com.estudo.hexagonal.adapters.in.controllers.request.ClienteRequest;
import com.estudo.hexagonal.adapters.in.controllers.response.ClienteResponse;
import com.estudo.hexagonal.application.core.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "isCpfValido", ignore = true)
    Cliente toCliente(ClienteRequest clienteRequest);
    ClienteResponse toClienteResponse(Cliente cliente);
}
