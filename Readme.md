Microservices Java Spring
=========================

A demo application designed to demonstrate the capabilities of the Java Spring Framework using a microservices architecture. This project is intended for educational purposes and helps illustrate how to structure and build microservices in Java.

Table of Contents
-----------------

1. [Description](#description)
2. [Installation](#installation)
3. [Usage](#usage)
4. [Microservices Architecture](#microservices-architecture)
5. [MVC Pattern](#mvc-pattern)
6. [Contributing](#contributing)
7. [Acknowledgements](#acknowledgements)

Description
-----------

This demo application is built using the Java Spring Framework and illustrates a microservices architecture. The project is divided into multiple services, such as:

*   **Product Service**

*   **Inventory Service**

*   **Order Service**

*   **Discovery Server** (using Spring Cloud Netflix Eureka)

*   **API Gateway** (using Spring Cloud Gateway)


The project demonstrates key concepts such as:

*   **MongoDB**: Used as a persistent database for services requiring NoSQL storage.

*   **MySQL**: For services that require relational database support with **Spring Data JPA**.

*   **Reactive Programming**: **Spring WebFlux** is used for reactive APIs, enabling non-blocking requests and responses.

*   **Eureka**: A service discovery mechanism, using Spring Cloud Netflix Eureka.

*   **Resilience4J**: For implementing **circuit breakers** to handle fault tolerance and failure recovery.


Installation
------------

To get this project running locally, follow these steps:

### Prerequisites

*   **Java 17 or higher**: The project is built using Java 17.

*   **Maven**: Apache Maven is used to manage the project dependencies and build the services.

*   **MongoDB**: If using MongoDB for persistence, ensure it's installed locally or use a cloud-based MongoDB instance (e.g., MongoDB Atlas).

*   **MySQL**: For services that require relational data, you’ll need a running MySQL instance or MySQL Docker container. If you don't want to run MySQL locally, you can use a cloud MySQL provider.

*   **IDE**: IntelliJ IDEA (or any IDE that supports Maven projects).

*   **Spring Boot 3.x**: This project uses Spring Boot 3.x as the foundation.

*   **Lombok**: The project uses **Lombok** to reduce boilerplate code in Java classes (e.g., getters, setters, constructors).


### Steps
1. ```
   git clone https://github.com/your-username/microservices-java-spring.git

2.  Open the project in IntelliJ IDEA.

3.  ```
    codemvn install

4.  **MongoDB**:

    *   If you're using a local MongoDB instance, ensure it's running on the default port (27017), or configure the connection in your application.properties or application.yml file.

    *   Alternatively, you can use a cloud-based MongoDB service like [MongoDB Atlas](https://www.mongodb.com/cloud/atlas).

5.  **MySQL**:

    *   If you're using MySQL locally, make sure it's running and configure the connection in the application.properties file (e.g., spring.datasource.url=jdbc:mysql://localhost:3306/yourdatabase).

    *   bashCopy codedocker run --name mysql -e MYSQL\_ROOT\_PASSWORD=root -p 3306:3306 -d mysql:latest

6.  Start the services:Each service can be run independently, or you can start them all together using the Maven build tool.

    *   bashCopy codemvn spring-boot:run -pl product-service

    *   bashCopy codemvn spring-boot:run


Microservices Architecture
--------------------------

Here is a high-level architecture diagram of the microservices:

![Microservices Architecture](https://spring.io/img/extra/microservices-6-dark.svg)

In this diagram:

*   The **API Gateway** handles all incoming requests and routes them to the appropriate service.

*   **Service Discovery (Eureka)** ensures that each service can find others without hardcoding URLs.

*   **Product, Inventory, and Order Services** are independent and interact with each other through REST APIs.

*   **Resilience4J Circuit Breaker** ensures fault tolerance in inter-service communication.


MVC Pattern
-----------

The **MVC** (Model-View-Controller) pattern is applied in the **Product Service** to handle the interactions between the database, business logic, and presentation layer.

Here’s an illustration of how **Product Service** implements the **MVC pattern**:

*   **Model**: The Product entity represents the data structure.

*   **View**: The response, which is typically in JSON format for RESTful services.

*   **Controller**: The ProductController handles HTTP requests, calls the **Service Layer**, and returns the appropriate **View**.


Usage
-----

Once the services are running, you can interact with them through the following endpoints:

*   **Product Service**: http://localhost:8080/product-service

*   **Inventory Service**: http://localhost:8081/inventory-service

*   **Order Service**: http://localhost:8082/order-service

*   **API Gateway**: http://localhost:8085/gateway

*   **Discovery Server (Eureka)**: http://localhost:8761/


You can access these endpoints using a browser or a tool like **Postman** to test API requests between the services. The **API Gateway** serves as a central entry point for all requests to the microservices.

Contributing
------------

Contributions are welcome! If you'd like to improve or extend this demo project, feel free to fork the repository and submit a pull request.

To contribute:

1.  Fork the repository.

2.  Create a new branch (git checkout -b feature-name).

3.  Make your changes.

4.  Commit your changes (git commit -m 'Add new feature').

5.  Push to your fork (git push origin feature-name).

6.  Open a pull request.

Acknowledgements
----------------

*   Special thanks to the Spring Boot and Spring Cloud communities for their excellent documentation and resources.

*   Inspired by [Spring Cloud Microservices](https://spring.io/projects/spring-cloud) tutorials and guides.


### Notes on Dependencies:

Your project uses several important dependencies and configurations:

*   **Spring Boot** (spring-boot-starter-parent): The foundation for building the microservices.

*   **Spring Cloud** (spring-cloud-dependencies): For managing service discovery (Eureka), API Gateway, and other cloud-native components.

*   **MongoDB** (spring-boot-starter-data-mongodb): For integrating MongoDB as the database for your services.

*   **MySQL** (spring-boot-starter-data-jpa & mysql-connector-j): For relational database support via **JPA** and the MySQL database.

*   **Spring Web** (spring-boot-starter-web): For building traditional web applications and RESTful services.

*   **Spring WebFlux** (spring-boot-starter-webflux): For building reactive, non-blocking APIs.

*   **Resilience4J** (spring-cloud-starter-circuitbreaker-resilience4j): For implementing fault tolerance and circuit breaker patterns.

*   **Lombok** (lombok): For reducing boilerplate code in your Java classes.

*   **Zipkin** (zipkin-reporter-brave, micrometer-tracing-bridge-brave): For distributed tracing to monitor and track service communication.

*   **Testcontainers** (testcontainers-bom): For containerized integration testing.

*   **Spring Boot Actuator**: For monitoring and managing the services in production.


The dependencies in the pom.xml are well-suited for a Spring-based microservices application with MongoDB and MySQL persistence, reactive programming support, service discovery, circuit breakers, and other cloud-native capabilities.