package com.estudo.hexagonal.adapters.out;

import com.estudo.hexagonal.application.ports.out.EnviarCpfValidacaoOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EnviarCpfValidacaoAdapter implements EnviarCpfValidacaoOutputPort {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void enviarParaValidacaoCpf(String cpf) {
        kafkaTemplate.send("tp-cpf-validacao", cpf);
    }
}
