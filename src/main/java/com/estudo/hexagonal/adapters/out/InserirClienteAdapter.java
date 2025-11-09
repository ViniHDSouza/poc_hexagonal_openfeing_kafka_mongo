package com.estudo.hexagonal.adapters.out;

import com.estudo.hexagonal.adapters.out.repository.ClienteRepository;
import com.estudo.hexagonal.adapters.out.repository.mapper.ClienteEntityMapper;
import com.estudo.hexagonal.application.core.domain.Cliente;
import com.estudo.hexagonal.application.ports.out.InserirClienteOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InserirClienteAdapter implements InserirClienteOutputPort {

    private ClienteRepository clienteRepository;
    private ClienteEntityMapper clienteEntityMapper;

    @Override
    public void inserir(Cliente cliente) {
        var clienteEntity = clienteEntityMapper.toClienteEntity(cliente);
        clienteRepository.save(clienteEntity);

    }
}
