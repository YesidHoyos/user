# user
Microservicio para la gestión de usuarios de la red social woloxgram

## Tabla de contenido
* [Tecnologías](#tecnologías)
* [Prerequisitos](#prerequisitos)
* [Instalación](#instalación)
* [Uso](#uso)

## Tecnologías
Proyecto creado con:
* Versión Java: 1.8
* Versión Spring-boot: 2.4.5
* Versión Maven: 3.6.1
* Eclipse sts

## Prerequisitos
* Versión Java 8 instalada
* Versión Maven: 3.6.1 or later instalada

## Instalación
Para correr este proyecto en local, ejecuta los siguientes comandos usando maven y java

```
$ cd /{workir}/user
$ mvn clean install
$ cd target
$ java -jar user-0.0.1-SNAPSHOT.jar
```
Para correr este proyecto en docker, ejecuta los siguientes comandos:
```
$ cd /{workir}/user
$ mvn clean install
$ (opcional, si la red aún no ha sido creada)docker network create --subnet 172.168.0.1/24 --gateway 172.168.0.2 -d bridge woloxgram-network
$ docker build -t user-microservice:1.0.0 .
$ docker run -d -p 9091:9091 --name UserMicroservice --network woloxgram-network --ip 172.168.0.22 user-microservice:1.0.0
```

## Uso
En el servicio user puede encontrar los siguientes endpoints
* **Obtener todos los usuarios -** obtiene todos los usuarios.\
Puedes crear una petición tipo GET a la siguiente url: 
* Si estas en local o ejecutando en docker  ```http://localhost:9091/users```
* Si vas a consumirlo de producción  ```http://woloxgram.us-east-2.elasticbeanstalk.com/users```

* **Obtener usuario por id -** obtiene el usuario dado el id de este.\
Puedes crear una petición tipo GET a la siguiente url: 
* Si estas en local o ejecutando en docker  ```http://localhost:9091/users/{userId}```
* Si vas a consumirlo de producción  ```http://woloxgram.us-east-2.elasticbeanstalk.com/users/{userId}```
