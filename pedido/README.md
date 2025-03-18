# SISTEMA DE PEDIDO

### TECNOLOGIAS
- Spring boot
- Mysql
- Kafka

## Passos para rodar a aplicação

- Na raiz do projeto executar o comando abaixo para criar o banco e o kafka
```
docker compose up
```

- No diretorio order/pedido executar o comando abaixo:
```
mvn clean install
java -jar target/pedido-0.0.1-SNAPSHOT.jar 
```

- No diretorio order/pagamento executar o comando abaixo:
```
mvn clean install
java -jar target/pagamento-0.0.1-SNAPSHOT.jar 
```

### Swagger das apis

* [API de Pedido](http://localhost:8080/swagger-ui/index.html#/)
* [API de Pagamento](http://localhost:8081/swagger-ui/index.html#/)

## Kafka UI
* [Kafka UI](http://localhost:8082/ui/clusters/local/all-topics?perPage=25)



