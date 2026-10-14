# Multi-Module Microservices Ecosystem

A decoupled, scalable backend system built on a microservice architecture using **Java**, **Spring Boot**, and containerized infrastructure.

## 🏗 Architecture Overview

This project is configured as a Maven Multi-Module project managed via a single parent configuration. It separates domain logic into dedicated autonomous applications:

*   **`product-service`**: Handles product inventory, querying, and storage using a NoSQL schema (**MongoDB**). Runs on port `8082`.
*   **`order-service`**: Manages transactional checkout flows and purchasing data records using a relational schema (**PostgreSQL**). Runs on port `8081`.
*   **Infrastructure Layer**: Centralized resource management via Docker Compose to manage shared backing services.

## 🚀 Tech Stack

*   **Java Versions**: Java 17 & Java 21
*   **Framework**: Spring Boot
*   **Build Tool**: Maven (Multi-Module Layout)
*   **Databases**: MongoDB (NoSQL) & PostgreSQL (Relational)
*   **Containerization**: Docker & Docker Compose
*   **Utilities**: Project Lombok (Annotation-driven boilerplate reduction)

## 📁 Repository Structure

```text
├── order-service/               # Relational Java 21 Microservice (Postgres)
├── product-service/             # NoSQL Java 17 Microservice (MongoDB)
├── docker-compose.infra.yaml    # Infrastructure orchestration file
├── pom.xml                      # Core Parent Maven configuration
└── README.md                    # Project documentation
```

## 🛠 Getting Started

### Prerequisites
*   Java 17 and Java 21 SDKs installed
*   Maven 3.x+
*   Docker & Docker Compose

### Local Development Setup

1. **Clone the Repository:**
   ```bash
   git clone https://github.com
   cd microservice-project
   ```

2. **Boot the Infrastructure (Docker):**
   Spin up MongoDB, PostgreSQL, and RabbitMQ containers locally in the background:
   ```bash
   docker-compose -f docker-compose.infra.yaml up -d
   ```

3. **Build the Entire Project:**
   Compile and package all sub-modules simultaneously from the root directory:
   ```bash
   mvn clean package -DskipTests
   ```

4. **Running the Services:**
   Open the root project directory in IntelliJ IDEA, activate the **Services Tool Window**, and start the applications:
    * Run `ProductServiceApplication` (Port: `8082`)
    * Run `OrderServiceApplication` (Port: `8081`)
