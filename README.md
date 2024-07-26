Projeto Autorizador – Yuri Monteiro Rufino

Instruções:

•	Para realizar requisições ao autorizador, utilizar o endpoint http://localhost:8080/authorize

O JSON a ser enviado para o autorizador deve seguir o seguinte padrão

{
    "id": 1,
    "accountId": 1,
    "totalAmount": 150,
    "mcc" :  "1234",
    "merchant": "RESTAURANTE DO ZE               SAO PAULO BR"
}

•	Para acessar o Swagger de documentação do projeto, basta acessar http://localhost:8080/swagger-ui/index.html#/authorization-controller

