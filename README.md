# EcomEase - Backend Microservices Application

EcomEase is a scalable backend microservices architecture designed for an e-commerce platform. This project demonstrates the use of modern technologies to build a distributed system using microservices. The system incorporates services for managing products, orders, carts, and more, while leveraging Spring Boot, Spring Cloud, RabbitMQ, Feign Clients, and other cutting-edge technologies. 

> Note this is still under development, any picking up of issues would be really rejoiced 🥳

## Features
- **Product Service**: Manage products, categories, availability, and pricing.
- **Cart Service**: Handle cart creation, management, and total price calculation.
- **Order Service**: Place and manage orders with automatic inventory updates (bonus: `coupons`).
- **Config Server**: Centralized configuration management using Spring Cloud Config.
- **Gateway Server**: API Gateway for routing and load balancing using Spring Cloud Gateway.
- **Eureka Server**: Service discovery and registry using Netflix Eureka.
- **Message Brokers**: Integration with RabbitMQ for asynchronous communication for inventory updates.
- **Feign Clients**: Inter-service communication using declarative REST clients.
- **Database Management**: SQL scripts for schema creation and seamless database integration.
- **Docker Containerization**: Using `docker-compose.yml` files for containerization of application.

## Architecture
The system is built with a modular architecture:

1. **Microservices**:
    - **Product Service**: Manages product inventory and pricing.
    - **Cart Service**: Handles cart operations and integrates with Product Service to calculate total price.
    - **Order Service**: Processes orders and updates product inventory.

2. **Infrastructure**:
    - **Gateway Server**: Acts as the single entry point for all client requests.
    - **Eureka Server**: Enables dynamic discovery of microservices.
    - **Config Server**: Provides centralized configuration management for all services.

3. **Communication**:
    - Synchronous communication using Feign Clients.
    - Asynchronous communication using RabbitMQ.

4. **Database**:
    - Each microservice has its own database.
    - Schema defined using SQL scripts for data consistency.

## Technologies Used
- **Spring Boot**
- **Spring Cloud Gateway**
- **Spring Cloud Eureka**
- **Spring Cloud Config**
- **Feign Clients**
- **RabbitMQ**
- **Hibernate** / **JPA**
- **H2 in-memory DB**
- **Docker**
- **Keycloak**

## Prerequisites
- **Java 17**
- **Maven**
- **Docker**
- **RabbitMQ**
- **H2/SQL**
- **Keycloak**

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/SahilAnower/EcomEase.git
cd EcomEase
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Start Services
Ensure you have Docker installed and running, then start the required dependencies (e.g., RabbitMQ) using Docker Compose. But first, generate images of
all microservices using `mvn compile jib:dockerBuild`. Then, move over to the docker-compose directory, and start the services in any of the environments i.e, prod/qa/default using below command.

```bash
docker-compose up
```
For running rabbitmq in local: `docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management`. This runs the rabbitmq server
on port 5672, and the management server on port 15672.

For auth, get into the Keycloak server console, and use, Client-Credentials/AccessCode-Credentials, by creating client and registering to the required realm, assign roles such as `PRODUCTS/ORDERS/CARTS`, for accessing all POST/PUT/DELETE requests inside of the application. Remember the Keycloak server by default would start on port 7080.

### 4. Run Individual Services
Navigate to each service folder and run the following:

```bash
mvn spring-boot:run
```

### 5. Access the Application
- **API Gateway**: http://localhost:8082
    - **Products**: http://localhost:8082/ecomease/products
    - **Carts**: http://localhost:8082/ecomease/carts
    - **Orders**: http://localhost:8082/ecomease/orders
- **Eureka Dashboard**: http://localhost:8070
- **RabbitMQ Management Console**: http://localhost:15672

## Configuration
Configuration is managed using Spring Cloud Config. The configuration files are stored in https://github.com/SahilAnower/EcomEase-config github project.

### Example Configurations
- **Product Service**: `products.yml`/`products-prod.yml`/`products-qa.yml`

## Database
Each microservice has its own database schema defined in the respective `src/main/resources/schema.sql` file.

[//]: # (### Schema Management)

[//]: # (To initialize the databases:)

[//]: # (1. Create the databases in MySQL for each service &#40;e.g., `products_db`, `carts_db`, `orders_db`&#41;.)

[//]: # (2. Run the SQL scripts from the respective microservices' `schema.sql` files.)

[//]: # (## API Endpoints)

[//]: # ()
[//]: # (### Product Service)

[//]: # (- `POST /products/bulk-check`: Bulk check for product availability and price.)

[//]: # (- `GET /products/{id}`: Get product details by ID.)

[//]: # ()
[//]: # (### Cart Service)

[//]: # (- `POST /carts`: Create a new cart.)

[//]: # (- `GET /carts/{id}`: Retrieve cart details.)

[//]: # ()
[//]: # (### Order Service)

[//]: # (- `POST /orders`: Create a new order.)

[//]: # (- `GET /orders/{id}`: Get order details.)

## Future Enhancements

[//]: # (- **Authentication and Authorization**: Add OAuth2/JWT integration for securing endpoints.)
- **Scaling**: Enhance auto-scaling using Kubernetes.
- **Monitoring**: Add centralized logging and monitoring with ELK stack or Prometheus/Grafana.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with detailed information about your changes.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

---

For any questions or support, please open an issue in the [repository](https://github.com/SahilAnower/EcomEase/issues) or contact [Sahil Anower](https://github.com/SahilAnower).
