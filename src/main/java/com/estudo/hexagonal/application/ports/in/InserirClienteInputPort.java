package com.estudo.hexagonal.application.ports.in;

import com.estudo.hexagonal.application.core.domain.Cliente;

public interface InserirClienteInputPort {
    void execute(Cliente cliente, String cep);
}
