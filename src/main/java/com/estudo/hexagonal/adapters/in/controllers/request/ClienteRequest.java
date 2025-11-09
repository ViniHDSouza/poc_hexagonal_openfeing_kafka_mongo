package com.estudo.hexagonal.adapters.in.controllers.request;

import com.estudo.hexagonal.application.core.domain.Endereco;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
        @NotBlank String nome,
        @NotBlank Endereco cep,
        @NotBlank String cpf
)
    {
    }
