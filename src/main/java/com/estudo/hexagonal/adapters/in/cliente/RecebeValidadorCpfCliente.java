package com.estudo.hexagonal.adapters.in.cliente;

import com.estudo.hexagonal.adapters.in.cliente.mapper.ClienteMensagemMapper;
import com.estudo.hexagonal.adapters.in.cliente.mensagem.ClienteMessagem;
import com.estudo.hexagonal.application.core.domain.Cliente;
import com.estudo.hexagonal.application.ports.in.AtualizarClienteInputPort;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RecebeValidadorCpfCliente {

    private AtualizarClienteInputPort atualizarClienteInputPort;
    private ClienteMensagemMapper clienteMensagemMapper;

    @KafkaListener(topics = "tp-cpf-validado", groupId = "hexagonal_group")
    public void recebedor(ClienteMessagem consumidor) {
        Cliente cliente = clienteMensagemMapper.toCliente(consumidor);
        atualizarClienteInputPort.execute(cliente, consumidor.getCep());
    }
}
