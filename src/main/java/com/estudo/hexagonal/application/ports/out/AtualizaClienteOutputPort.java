package com.estudo.hexagonal.application.ports.out;

import com.estudo.hexagonal.application.core.domain.Cliente;

public interface AtualizaClienteOutputPort {
    void atualizar(Cliente cliente, String cep);
}
