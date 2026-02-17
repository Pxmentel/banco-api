# Banco API 💰

A robust banking REST API built with **Spring Boot 3** to demonstrate advanced backend concepts, data persistence, and software architecture.

This project evolved from an in-memory simulation to a production-ready persistent solution using **PostgreSQL**, exploring polymorphism and database inheritance mapping.

---

## 🚀 Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3.3.5
* **Persistence:** Spring Data JPA / Hibernate
* **Database:** PostgreSQL
* **Productivity:** Lombok
* **Documentation:** Swagger (OpenAPI)
* **Validation:** Bean Validation (Hibernate Validator)

---

## 🏗️ Architecture & Technical Highlights

### 🧬 Inheritance Mapping (JPA)
Utilizes the **InheritanceType.SINGLE_TABLE** strategy to manage different account types (`CheckingAccount` and `SavingsAccount`) within a single table using a discriminator column. This showcases:
* Database and application-level polymorphism.
* Encapsulation of specific business rules (e.g., overdraft limits exclusive to Checking Accounts).

### 📊 Performance with JPQL
Implemented custom queries using **@Query** and **Derived Query Methods** to delegate financial calculations to the database engine (such as total balance across all accounts), ensuring scalability and memory efficiency.

### 🛡️ Reliability & Security
* **Global Exception Handling:** Centralized error management, converting business exceptions into semantic HTTP responses (400, 404, etc.).
* **Transactional Integrity:** Use of `@Transactional` to ensure atomicity in critical operations like transfers, with automatic rollback on failures.

---

## 📦 Core Features

* **Account Management:** Dynamic creation of Checking or Savings accounts via a single endpoint.
* **Banking Operations:** Deposit, Withdrawal, and Transfer with strict validation rules.
* **Financial Statistics:** Total balance calculation, highest balance search, and minimum balance filters.
* **Relationships:** `Many-to-One` mapping between Accounts and Clients with cascading persistence.

---

## ▶️ Running the Project

### 1️⃣ Prerequisites
* Java 17+
* PostgreSQL running locally

### 2️⃣ Database Configuration
In `src/main/resources/application.properties`, set your credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
### 3️⃣ Execution
```
./mvnw spring-boot:run
```

## 📘 API Documentation
Access and test endpoints via Swagger UI:
`http://localhost:8080/swagger-ui.html`

## 📌 Roadmap (Next Challenges)
[ ] Security: Spring Security + JWT implementation.

[ ] Containerization: Dockerizing application and database.

[ ] Quality: CI/CD Pipeline with GitHub Actions.

## 👨‍💻 Author
Vinicius Pimentel da Silva
* GitHub: Pxmentel