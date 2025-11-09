package com.estudo.hexagonal.application.ports.out;

import com.estudo.hexagonal.application.core.domain.Cliente;

import java.util.Optional;

public interface ProcurarClientePorIdOutputPort {
    Optional<Cliente> procurarClientePorId(String id);
}
