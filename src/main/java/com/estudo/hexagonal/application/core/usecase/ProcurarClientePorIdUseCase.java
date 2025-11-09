package com.estudo.hexagonal.application.core.usecase;

import com.estudo.hexagonal.application.core.domain.Cliente;
import com.estudo.hexagonal.application.ports.in.ProcurarClientePorIdInputPort;
import com.estudo.hexagonal.application.ports.out.ProcurarClientePorIdOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProcurarClientePorIdUseCase implements ProcurarClientePorIdInputPort {

    private final ProcurarClientePorIdOutputPort procurarClientePorIdOutputPort;

    @Override
    public Cliente execute(String id) {
        Optional<Cliente> cliente = procurarClientePorIdOutputPort.procurarClientePorId(id);
        return cliente
                .orElseThrow(
                        () -> new RuntimeException("Cliente não encontrado")
                );
    }
}
