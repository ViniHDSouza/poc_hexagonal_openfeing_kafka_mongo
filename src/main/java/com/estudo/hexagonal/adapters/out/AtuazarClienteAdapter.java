package com.estudo.hexagonal.adapters.out;

import com.estudo.hexagonal.adapters.out.repository.ClienteRepository;
import com.estudo.hexagonal.adapters.out.repository.entity.ClienteEntity;
import com.estudo.hexagonal.adapters.out.repository.mapper.ClienteEntityMapper;
import com.estudo.hexagonal.application.core.domain.Cliente;
import com.estudo.hexagonal.application.ports.out.AtualizaClienteOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AtuazarClienteAdapter implements AtualizaClienteOutputPort {

    private ClienteRepository clienteRepository;
    private ClienteEntityMapper clienteEntityMapper;

    @Override
    public void atualizar(Cliente cliente, String cep) {
        ClienteEntity clienteEntity = clienteEntityMapper.toClienteEntity(cliente);
        clienteRepository.save(clienteEntity);
    }
}
