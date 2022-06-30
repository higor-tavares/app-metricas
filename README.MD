# APP METRICAS
Projeto de estudos da integração do Spring Boot e o Prometheus.
## Requisitos

* Docker
* Java 11
* Make (Opcional mas facilita)


## Como executar?  

Primeiramente renomeie o arquivo prometheus.sample.yml para prometheus.yml e em seguida substitua em target a string "SEU IP" pelo ip da sua maquina, Em seguida rode o comando `make dependencies`, onde será iniciado o container do Prometheus.
Em seguida rode um projeto na sua IDE.

## Como testar?

Uma vez que seu projeto está rodando acesse http://localhost:8080/true ou http://localhost:8080/false para gerar metricas
Uma vez execultados os passos acima, acesse http://localhost:8080/actuator/prometheus
onde é possivel ver as metricas geradas pela aplicação.

