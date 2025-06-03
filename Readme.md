# 📦 Task Management App – Backend

Backend API for a task management application using Spring Boot and JWT authentication.

## ✅ Requirements

- Java 16+
- Maven
- PostgreSQL
- Spring Boot 3+

## 🔐 Authentication

- JWT-based authentication
- Token must be included in the header as `Authorization: Bearer <token>`
- Endpoints under `/auth/**` are public; all others require a valid token

## 📚 API Endpoints

### 📝 Tasks

| Method | Endpoint         | Auth Required | Description                      |
|--------|------------------|---------------|----------------------------------|
| POST   | `/tasks`         | ✅ Yes        | Create a new task (title required) |
| GET    | `/tasks`         | ❌ No         | Retrieve all tasks              |
| PATCH  | `/tasks/:id`     | ✅ Yes        | Toggle task completion status   |
| DELETE | `/tasks/:id`     | ✅ Yes        | Delete a task                   |

### 🔐 Auth

| Method | Endpoint         | Description              |
|--------|------------------|--------------------------|
| POST   | `/auth/register` | Register a new user      |
| POST   | `/auth/login`    | Login and retrieve token |

## 🧱 Tech Stack

- Spring Boot
- Spring Security + JWT
- JPA/Hibernate
- PostgreSQL
- Maven

## ⚙️ Getting Started

### 1. Clone the Project

```bash
git clone https://github.com/alfindwi/artee-be.git
cd artee-be
```

### 2. Configure Database
Edit the application.properties file:
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/artee_db
spring.datasource.username=root
spring.datasource.password=yourpassword
jwt.secret=your_jwt_secret_key
```

### 3. Run App
```bash
mvn spring-boot:run
```


### 🧪 Sample cURL CommandsL
```bash
# Login
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "user@example.com", "password": "yourpassword"}'

# Create task
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{"title": "Do Homework"}'
```

