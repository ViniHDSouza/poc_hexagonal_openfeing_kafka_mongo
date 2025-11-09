package com.estudo.hexagonal.application.core.usecase;

import com.estudo.hexagonal.application.core.domain.Cliente;
import com.estudo.hexagonal.application.core.domain.Endereco;
import com.estudo.hexagonal.application.ports.in.AtualizarClienteInputPort;
import com.estudo.hexagonal.application.ports.in.ProcurarClientePorIdInputPort;
import com.estudo.hexagonal.application.ports.out.AtualizaClienteOutputPort;
import com.estudo.hexagonal.application.ports.out.ProcurarClientePorIdOutputPort;
import com.estudo.hexagonal.application.ports.out.ProcurarEnderecoPorCepOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AtualizarClienteUseCase implements AtualizarClienteInputPort {

    private final ProcurarClientePorIdInputPort procurarClientePorIdInputPort;
    private final ProcurarEnderecoPorCepOutputPort procurarEnderecoPorCepOutputPort;
    private final AtualizaClienteOutputPort atualizaClienteOutputPort;

    @Override
    public void execute(Cliente cliente, String cep) {
        procurarClientePorIdInputPort.execute(cliente.getId());//eu so faco a busca para saber se o cliente existe....nada mais
        Endereco endereco = procurarEnderecoPorCepOutputPort.procurar(cep);
        cliente.setEndereco(endereco);
        this.atualizaClienteOutputPort.atualizar(cliente,cep);
    }

}
