package com.estudo.hexagonal.adapters.in.controllers.response;

import com.estudo.hexagonal.adapters.out.repository.entity.EnderecoEntity;

public record ClienteResponse(
        String nome,
        EnderecoResponse endereco,
        String cpf,
        Boolean isCpfValido) {
}
