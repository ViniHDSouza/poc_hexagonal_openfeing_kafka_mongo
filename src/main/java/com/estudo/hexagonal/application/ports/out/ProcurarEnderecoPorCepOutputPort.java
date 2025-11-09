package com.estudo.hexagonal.application.ports.out;

import com.estudo.hexagonal.application.core.domain.Endereco;

public interface ProcurarEnderecoPorCepOutputPort {

    Endereco procurar(String cep);

}
