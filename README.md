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

Resposta questão aberta:

Para evitar que duas transações ao mesmo tempo interfiram uma na outra, pode ser utilizada uma abordagem de lock(bloqueio). Esse bloqueio pode ser feito de diferentes maneiras, como um bloqueio no banco de dados, que impeça que outros processos, ou threads acessem o mesmo recurso. Para realizar o bloqueio, nós podemos usar como identificador único o número do cartão, e impedir que outras threads processem outra transação com o cartão até a primeira transação seja liberada. Esse tipo de bloqueio também pode ser feito a nível de aplicação.
