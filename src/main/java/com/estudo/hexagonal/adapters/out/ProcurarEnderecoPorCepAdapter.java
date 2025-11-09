package com.estudo.hexagonal.adapters.out;

import com.estudo.hexagonal.adapters.out.client.ProcurarEnderecoPorCepClient;
import com.estudo.hexagonal.adapters.out.client.mapper.EnderecoResponseMapper;
import com.estudo.hexagonal.adapters.out.client.response.EnderecoResponse;
import com.estudo.hexagonal.application.core.domain.Endereco;
import com.estudo.hexagonal.application.ports.out.ProcurarEnderecoPorCepOutputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProcurarEnderecoPorCepAdapter implements ProcurarEnderecoPorCepOutputPort {

    private ProcurarEnderecoPorCepClient procurarEnderecoPorCepClient;
    private EnderecoResponseMapper enderecoResponseMapper;


    @Override
    public Endereco procurar(String cep) {
        var enderecoResponse = procurarEnderecoPorCepClient.procurar(cep);
        return enderecoResponseMapper.toEndereco(enderecoResponse);
    }
}
