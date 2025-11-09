package com.estudo.hexagonal.adapters.in.controllers;

import com.estudo.hexagonal.adapters.in.controllers.mapper.ClienteMapper;
import com.estudo.hexagonal.adapters.in.controllers.request.ClienteRequest;
import com.estudo.hexagonal.adapters.in.controllers.response.ClienteResponse;
import com.estudo.hexagonal.application.core.domain.Cliente;
import com.estudo.hexagonal.application.ports.in.AtualizarClienteInputPort;
import com.estudo.hexagonal.application.ports.in.DeletarClientePorIdInputPort;
import com.estudo.hexagonal.application.ports.in.InserirClienteInputPort;
import com.estudo.hexagonal.application.ports.in.ProcurarClientePorIdInputPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clientes")
@AllArgsConstructor
@NoArgsConstructor
public class ClienteController {

    private InserirClienteInputPort inserirClienteInputPort;
    private ProcurarClientePorIdInputPort procurarClientePorIdInputPort;
    private AtualizarClienteInputPort atualizarClienteInputPort;
    private DeletarClientePorIdInputPort deletarClientePorIdInputPort;
    private ClienteMapper clienteMapper;

    @PostMapping
    public ResponseEntity<Void> inserir(@Valid  @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.toCliente(clienteRequest);
        inserirClienteInputPort.execute(cliente,clienteRequest.cep().toString());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> procurarPorId(@PathVariable String id) {
        Cliente cliente = procurarClientePorIdInputPort.execute(id);
        ClienteResponse clienteResponse = clienteMapper.toClienteResponse(cliente);
        return ResponseEntity.ok(clienteResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable String id, @Valid  @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.toCliente(clienteRequest);
        cliente.setId(id);
        atualizarClienteInputPort.execute(cliente,clienteRequest.cep().toString());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaPorId(@PathVariable String id) {
        deletarClientePorIdInputPort.execute(id);
        return ResponseEntity.noContent().build();
    }


}
