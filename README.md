# Arquitetura Hexagonal - Exemplo Prático com Spring Boot

## Sumário
1. [Visão Geral](#visão-geral)
2. [Arquitetura Hexagonal](#arquitetura-hexagonal)
3. [Estrutura do Projeto](#estrutura-do-projeto)
4. [Fluxos de Funcionamento](#fluxos-de-funcionamento)
5. [Configuração e Execução](#configuração-e-execução)

## Visão Geral

Este projeto demonstra a implementação prática da Arquitetura Hexagonal (também conhecida como Ports & Adapters) em uma aplicação Spring Boot. O domínio de negócio é um sistema de gerenciamento de clientes que integra:
- Validação assíncrona de CPF via Kafka
- Consulta de endereços via serviço externo (OpenFeign)
- Persistência em MongoDB

## Arquitetura Hexagonal

### Diagrama Conceitual
```
                  ┌──────────────────────────────────────┐
                  │           【Hexágono】                │
      REST       │                                      │    MongoDB
    ─────────────▶    ┌─────────────────┐              │   ──────────▶
                  │   │                 │               │
    Kafka Input  │    │     Domain      │   Output     │    Kafka Output
    ─────────────▶    │     Logic       │   Ports      │    ──────────▶
                  │   │                 │               │
                  │   └─────────────────┘              │    OpenFeign
                  │            ▲                        │    ──────────▶
                  │         Input                      │
                  │         Ports                      │
                  └──────────────────────────────────────┘
```

### Diagrama Detalhado de Componentes
```
┌─────────────────┐        ┌──────────────────────────────┐        ┌────────────────────┐
│   Adapters In   │        │        Hexagon Core          │        │   Adapters Out     │
│                 │        │                              │        │                     │
│ ClienteController│───┐    │    ┌──────────────┐         │    ┌───│MongoDBAdapter      │
│                 │   │    │    │   Domain     │         │    │   │                     │
│ KafkaListener   │   │    │    │   Cliente    │         │    │   │ FeignClient        │
│                 │   │    │    │   Endereco   │         │    │   │                     │
│                 │   │    │    └──────────────┘         │    │   │ KafkaProducer      │
│                 │   │    │           ▲                 │    │   │                     │
│                 │   │    │           │                 │    │   └────────────────────┘
│                 │   │    │    ┌──────────────┐         │    │
│                 │   └───▶│    │  Use Cases   │         │    │
│                 │        │    │              │         │    │
└─────────────────┘        │    └──────────────┘         │    │
                          │            │                 │    │
                          │    ┌──────────────┐         │    │
                          │    │    Ports     │         │    │
                          │    │   In | Out   │─────────┘    │
                          │    └──────────────┘              │
                          └──────────────────────────────────┘
```

### Componentes da Arquitetura

1. **Domain (Núcleo)**
   - `Cliente.java`: Entidade principal
   - `Endereco.java`: Value Object
   - Localização: `application.core.domain`

2. **Portas (Interfaces)**
   - Input Ports:
     ```java
     public interface InserirClienteInputPort {
         void execute(Cliente cliente, String cep);
     }
     ```
   - Output Ports:
     ```java
     public interface ProcurarEnderecoPorCepOutputPort {
         Endereco procurar(String cep);
     }
     ```

3. **Use Cases**
   - Implementam Input Ports
   - Orquestram operações do domínio
   - Exemplo:
     ```java
     @Service
     public class InserirClienteUseCase implements InserirClienteInputPort {
         private final ProcurarEnderecoPorCepOutputPort enderecoPort;
         private final InserirClienteOutputPort clientePort;
         private final EnviarCpfValidacaoOutputPort kafkaPort;

         @Override
         public void execute(Cliente cliente, String cep) {
             var endereco = enderecoPort.procurar(cep);
             cliente.setEndereco(endereco);
             clientePort.inserir(cliente);
             kafkaPort.enviarParaValidacaoCpf(cliente.getCpf());
         }
     }
     ```

4. **Adapters**
   - Input (Driving) Adapters:
     - REST Controllers
     - Kafka Listeners
   - Output (Driven) Adapters:
     - MongoDB Repository
     - Feign Client
     - Kafka Producer

## Fluxos de Funcionamento

### 1. Inserção de Cliente
```
┌──────────┐    ┌─────────────┐    ┌──────────────┐    ┌──────────┐
│  REST    │──▶ │ Input Port  │──▶ │  Use Case    │──▶ │ MongoDB  │
│ Request  │    │ (Interface) │    │ Implementation│    │ Adapter  │
└──────────┘    └─────────────┘    └──────────┬───┘    └──────────┘
                                              │
                                              │         ┌──────────┐
                                              └────────▶│  Kafka   │
                                                       │ Producer  │
                                                       └──────────┘
```

1. Request HTTP chega ao Controller
2. Controller converte DTO para objeto de domínio
3. Use Case:
   - Busca endereço via CEP
   - Persiste cliente
   - Publica evento de validação CPF

### 2. Validação de CPF (Assíncrona)
```
┌──────────┐    ┌─────────────┐    ┌──────────────┐
│  Kafka   │──▶ │ Input Port  │──▶ │  Use Case    │
│ Consumer │    │ (Interface) │    │ Implementation│
└──────────┘    └─────────────┘    └──────────────┘
```

## Configuração e Execução

### Pré-requisitos
- Java 11+
- Docker e Docker Compose
- Maven

### 1. Iniciando Dependências
```bash
docker-compose up -d
```

### 2. WireMock (Mock do Serviço de Endereço)
```bash
cd wiremock
java -jar wiremock-standalone-4.0.0-beta.15.jar --port 8082
```

Exemplo de mapping (endereco.json):
```json
{
  "request": {
    "method": "GET",
    "url": "/endereco/38400000"
  },
  "response": {
    "status": 200,
    "jsonBody": {
      "street": "Rua Dos Bobos",
      "city": "Rio de Janeiro",
      "state": "Rio de Janeiro"
    }
  }
}
```

### 3. Executando a Aplicação
```bash
./mvnw spring-boot:run
```

## Benefícios desta Implementação

1. **Isolamento do Domínio**
   - Regras de negócio protegidas de detalhes externos
   - Fácil de testar e manter

2. **Flexibilidade**
   - Troca de tecnologias sem afetar o core
   - Ex: Mudar MongoDB para PostgreSQL requer apenas novo adapter

3. **Testabilidade**
   - Use Cases testáveis sem infraestrutura
   - Mocks apenas nas bordas (ports)

4. **Manutenibilidade**
   - Separação clara de responsabilidades
   - Dependências sempre apontam para dentro

## Endpoints REST

### Inserir Cliente
```http
POST /api/v1/clientes
Content-Type: application/json

{
  "nome": "João Silva",
  "cpf": "12345678900",
  "cep": "38400000"
}
```

### Buscar Cliente
```http
GET /api/v1/clientes/{id}
```

## Dicas de Desenvolvimento

1. Comece pelo domínio (Cliente, Endereco)
2. Defina as portas (interfaces)
3. Implemente os casos de uso
4. Crie os adaptadores

## Troubleshooting

- **WireMock não responde**: Verifique porta 8082 e mappings
- **MongoDB não conecta**: Confirme container e porta 27017
- **Kafka**: Verifique tópicos e conectividade

---

Para mais detalhes, consulte o código fonte nos caminhos mencionados ou abra uma issue.

Para executar o WireMock deve executar o seguinte comando:
java -jar wiremock-standalone-4.0.0-beta.15.jar --port 8082
dentro da pasta, vai criar duas pastas. Dentro da pasta mapping incluir os seguintes jsons (representam o request e response mokados):

endereco.json : [
{
"request": {
"method": "GET",
"url": "/endereco/38400000"
},
"response": {
"status": 200,
"headers": {
"Content-Type": "application/json"
},
"jsonBody": {
"street": "Rua Dos Bobos",
"city": "Rio de Janeiro",
"state": "Rio de Janeiro"
}
}
}
]

endereco_2.json : [
{
"request": {
"method": "GET",
"url": "/endereco/38400001"
},
"response": {
"status": 200,
"headers": {
"Content-Type": "application/json"
},
"jsonBody": {
"street": "Rua Numero Zero",
"city": "São Paulo",
"state": "São Paulo"
}
}
}

]

Observavao: Toda vez que alterar a configuracao do WireMock, deve reiniciar o servidor.
