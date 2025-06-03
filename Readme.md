# 📦 Task Management App – Backend

Backend API untuk aplikasi manajemen tugas menggunakan Spring Boot dan JWT authentication.

## ✅ Requirements

- Java 16+
- Maven
- PostgreSQL
- Spring Boot 3+

## 🔐 Authentication

- JWT-based authentication
- Token harus dikirim lewat `Authorization: Bearer <token>`
- Endpoints `/auth/**` bebas diakses, lainnya membutuhkan token valid

## 📚 API Endpoints

### 📝 Tasks

| Method | Endpoint         | Auth Required | Description                      |
|--------|------------------|---------------|----------------------------------|
| POST   | `/tasks`         | ✅ Yes        | Create new task (title required)|
| GET    | `/tasks`         | ❌ No         | List all tasks                  |
| PATCH  | `/tasks/:id`     | ✅ Yes        | Toggle task completion          |
| DELETE | `/tasks/:id`     | ✅ Yes        | Delete a task                   |

### 🔐 Auth

| Method | Endpoint         | Description          |
|--------|------------------|----------------------|
| POST   | `/auth/register` | Register new user    |
| POST   | `/auth/login`    | Login and get token  |

## 🧱 Tech Stack

- Spring Boot
- Spring Security + JWT
- JPA/Hibernate
- PostgreSQL
- Maven

## ⚙️ Run Locally

### 1. **Clone project**

```bash
git clone https://github.com/your-name/task-backend.git
cd task-backend
```

### 2. Configure DB in application.properties
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


### 🧪 Sample cURL
```bash
# Login
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "user@example.com", "password": "yourpassword"}'

# Create task
curl -X POST http://localhost:8080/tasks \
  -H "Authorization: Bearer <your_token>" \
  -H "Content-Type: application/json" \
  -d '{"title": "Do Homework"}'
```

