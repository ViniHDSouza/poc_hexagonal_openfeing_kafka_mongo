package com.estudo.hexagonal.adapters.in.controllers.response;

public record EnderecoResponse(
        String cep,
        String rua,
        String numero,
        String cidade,
        String estado
) {
}
