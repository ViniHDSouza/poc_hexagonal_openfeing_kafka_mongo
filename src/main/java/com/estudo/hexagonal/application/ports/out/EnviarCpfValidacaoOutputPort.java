package com.estudo.hexagonal.application.ports.out;

public interface EnviarCpfValidacaoOutputPort {
    void enviarParaValidacaoCpf(String cpf);
}
