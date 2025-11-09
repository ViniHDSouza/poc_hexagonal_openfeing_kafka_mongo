package com.estudo.hexagonal.application.core.usecase;

import com.estudo.hexagonal.application.core.domain.Cliente;
import com.estudo.hexagonal.application.ports.in.InserirClienteInputPort;
import com.estudo.hexagonal.application.ports.out.EnviarCpfValidacaoOutputPort;
import com.estudo.hexagonal.application.ports.out.InserirClienteOutputPort;
import com.estudo.hexagonal.application.ports.out.ProcurarEnderecoPorCepOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InserirClienteUseCase implements InserirClienteInputPort {

    private final ProcurarEnderecoPorCepOutputPort procurarEnderecoPorCepOutputPort;
    private final InserirClienteOutputPort inserirClienteOutputPort;
    private final EnviarCpfValidacaoOutputPort enviarCpfValidacaoOutputPort;


    @Override
    public void execute(Cliente cliente, String cep) {
        var endereco = procurarEnderecoPorCepOutputPort.procurar(cep);
        cliente.setEndereco(endereco);
        inserirClienteOutputPort.inserir(cliente);
        enviarCpfValidacaoOutputPort.enviarParaValidacaoCpf(cliente.getCpf());

    }
}
