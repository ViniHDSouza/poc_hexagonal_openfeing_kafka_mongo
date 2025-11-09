package com.estudo.hexagonal.adapters.out;

import com.estudo.hexagonal.adapters.out.repository.ClienteRepository;
import com.estudo.hexagonal.adapters.out.repository.entity.ClienteEntity;
import com.estudo.hexagonal.adapters.out.repository.mapper.ClienteEntityMapper;
import com.estudo.hexagonal.application.core.domain.Cliente;
import com.estudo.hexagonal.application.ports.out.ProcurarClientePorIdOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class ProcurarClientePorIdAdapter implements ProcurarClientePorIdOutputPort {

    private ClienteRepository clienteRepository;
    private ClienteEntityMapper clienteEntityMapper;

    @Override
    public Optional<Cliente> procurarClientePorId(String id) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(id);
        return clienteEntity.map(entity -> clienteEntityMapper.toCliente(entity));
    }
}
