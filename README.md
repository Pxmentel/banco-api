# Banco API 💰

A simple banking REST API built with Spring Boot to practice backend fundamentals such as layered architecture, DTOs, validation, exception handling, and unit testing.

This project simulates bank account operations using in-memory storage (`Map` / `List`) — no database — focusing on core Java and API design skills.

---

## 🚀 Tech Stack

* Java 17
* Spring Boot 3
* Maven
* Bean Validation
* JUnit / Mockito
* Swagger (OpenAPI)

---

## 📦 Features

* Create account
* List accounts
* Search account by number
* Deposit
* Withdraw
* Transfer between accounts
* Total balance calculation
* Account statistics (count, highest balance, filters)
* DTO mapping
* Global exception handling
* Input validation
* Unit tests

---

## ▶️ Running the Project

### 1️⃣ Clone repository

```bash
git clone https://github.com/Pxmentel/banco-api.git
cd banco-api
```

### 2️⃣ Run the application

Using Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Application will start at:

```
http://localhost:8080
```

---

## 📘 API Documentation

Swagger UI available at:

```
http://localhost:8080/swagger-ui.html
```

---

## 🧪 Running Tests

```bash
./mvnw test
```

---

## 🧠 Project Purpose

This project was developed as a learning exercise to strengthen:

* Object-oriented design
* REST API structure
* Service layer logic
* Exception handling
* Clean code practices
* Testing fundamentals

---

## 📌 Future Improvements

* Persist data with PostgreSQL
* Authentication (Spring Security + JWT)
* Docker containerization
* Integration tests
* CI/CD pipeline

---

## 👨‍💻 Author

Vinicius Pimentel
GitHub: https://github.com/Pxmentel

---
