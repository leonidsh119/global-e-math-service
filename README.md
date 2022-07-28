# Global-e Math Service
Spring-boot Microservice for math operations. Currently supports "Prime" API that provides an endpoint method for checking if a number is prime.

## 1. Build
The source code in Java spring-boot can be found here:
https://github.com/leonidsh119/global-e-math-service

Build the project using Maven. Run the following command from the project root directory: 
````
mvn clean install
````

To crete docker container with the service, run
````
mvn clean install -Pdocker
````

## 2. Deploy
The docker image with the service can be found here:
https://hub.docker.com/repository/docker/leonidsh/global-e-math-service-server

The docker container exposes port 80 for HTTP interface.
Run the docker image mapping the container port 80 to the host port according your needs.
````
docker run -p <host-port>:80 leonidsh/global-e-math-service-server
````

For example, if you like to map the service API in the container to host port 80, run as following:
````
docker run -p 80:80 leonidsh/global-e-math-service-server
````

## 3. Test
The service provides API visibility and documentation via Swagger UI:
http://localhost/math/swagger-ui/index.html