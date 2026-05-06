# Secure Project Management System Backend

A backend-focused Project Management System built using Spring Boot with a major focus on learning Spring Security, JWT Authentication, and secure REST API development.

This project helped me understand how authentication and authorization actually work internally instead of just using annotations without understanding the flow behind them.

---

## 🚀 Features

* User Registration & Login
* JWT Authentication
* Access & Refresh Tokens
* Role-Based Authorization (USER / ADMIN)
* Protected REST APIs
* Project Management APIs
* Task Management APIs
* Task Status Update APIs
* Ownership-based Access Control
* Global Exception Handling
* Validation using Jakarta Validation

---

## 🔐 Security Features

* Stateless authentication using JWT
* Secure API access using Bearer Tokens
* Role-based authorization
* Users cannot access or modify resources belonging to other users
* Protected endpoints using Spring Security

---

## 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Security
* JWT
* Hibernate / JPA
* MySQL
* Maven
* REST APIs
* Postman

---

## 📚 What I Learned

During this project, I learned and explored:

* Spring Security authentication flow
* JWT token generation and validation
* How Bearer Tokens work internally
* Stateless authentication
* DTO mapping using ModelMapper
* Global Exception Handling
* Role-based Authorization
* REST API security best practices

I also faced and debugged multiple backend issues involving:

* ModelMapper mappings
* DTO inconsistencies
* Enum validation
* Spring Security authentication flow debugging



## ▶️ Run Locally

### 1️⃣ Clone Repository

```bash
git clone https://github.com/ankitkulria/pms-secure-system.git
```

### 2️⃣ Configure MySQL Database

Create a MySQL database and update your configuration.

### 3️⃣ Add application.properties

Create:

```text
src/main/resources/application.properties
```

Add your configuration:

```properties
spring.datasource.url=YOUR_DB_URL
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

jwt.secret=YOUR_SECRET_KEY
```

### 4️⃣ Run Application

Run the Spring Boot application.

---

## 👨‍💻 Author

Ankit Suthar

---

## 📌 Future Improvements

* Frontend Integration
* Swagger Documentation
* Docker Support
* Unit & Integration Testing
* Deployment
