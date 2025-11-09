package com.estudo.hexagonal.application.core.usecase;

import com.estudo.hexagonal.application.core.domain.Cliente;
import com.estudo.hexagonal.application.ports.in.DeletarClientePorIdInputPort;
import com.estudo.hexagonal.application.ports.in.ProcurarClientePorIdInputPort;
import com.estudo.hexagonal.application.ports.out.DeletarClientePorIdOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletarClientePorIdUseCase implements DeletarClientePorIdInputPort {

    private final ProcurarClientePorIdInputPort procurarClientePorIdInputPort;
    private final DeletarClientePorIdOutputPort deletarClientePorIdOutputPort;

    public void execute(String id) {
        procurarClientePorIdInputPort.execute(id);
        deletarClientePorIdOutputPort.deletarClientePorId(id);
    }
}
