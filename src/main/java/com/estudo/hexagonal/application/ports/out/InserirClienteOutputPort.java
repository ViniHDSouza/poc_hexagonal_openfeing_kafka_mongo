package com.estudo.hexagonal.application.ports.out;

import com.estudo.hexagonal.application.core.domain.Cliente;

public interface InserirClienteOutputPort {
    void inserir(Cliente cliente);
}
