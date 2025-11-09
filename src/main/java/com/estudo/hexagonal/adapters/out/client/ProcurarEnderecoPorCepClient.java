package com.estudo.hexagonal.adapters.out.client;

import com.estudo.hexagonal.adapters.out.client.response.EnderecoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "procurar-endereco-por-cep-client",
        url = "${busca.cliente.endereco.url}"
)
public interface ProcurarEnderecoPorCepClient {

    @GetMapping("/{cep}")
    EnderecoResponse procurar(@PathVariable("cep") String cep);

}