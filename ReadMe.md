## Pre-requisites
1. [Java 11](https://www.azul.com/downloads/?version=java-11-lts&package=jdk)
2. [docker](https://docs.docker.com/get-docker/)

## Installation
Docker command to run the kafka and akhq
```sh
docker-compose  -f compose.yml up
```

click: [akhq](http://localhost:8080/) to check kafka 

click: [swagger](http://localhost:9090/swagger-ui.html) to test from swagger ui

click: [h2](http://localhost:9090/h2-console) to check db
    
    login with url, username & pwd as per application.properties

https://www.baeldung.com/java-reactive-systems