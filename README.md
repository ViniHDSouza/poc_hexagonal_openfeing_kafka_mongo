


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
