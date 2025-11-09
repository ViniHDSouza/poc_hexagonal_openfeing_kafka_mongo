package com.estudo.hexagonal.adapters.out;

import com.estudo.hexagonal.adapters.out.repository.ClienteRepository;
import com.estudo.hexagonal.application.ports.out.DeletarClientePorIdOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeletarClientePorIdAdapter implements DeletarClientePorIdOutputPort {

    private ClienteRepository clienteRepository;

    @Override
    public void deletarClientePorId(String id) {
        clienteRepository.deleteById(id);
    }
}
