Spring Security JWT
A Spring Boot application implementing JSON Web Token (JWT) based authentication and authorization using Spring Security. This project supports user registration, login, and role-based access control (RBAC) for USER and ADMIN roles, with a MySQL database for persistent storage.
Features

User Registration: Register users with a username, password, and role (USER or ADMIN).
JWT Authentication: Generate and validate JWTs for secure stateless authentication.
Role-Based Authorization: Restrict access to endpoints based on user roles.
Spring Security: Secure API endpoints using Spring Security filters.
MySQL Integration: Store user data in a MySQL database using Spring Data JPA.

Prerequisites

Java: JDK 17 or later
Maven: 3.8.0 or later
MySQL: 8.0 or later
IDE: IntelliJ IDEA, Eclipse, or VS Code (optional)
curl or Postman: For testing API endpoints

Project Structure
spring-security-jwt/
├── src/
│   ├── main/
│   │   ├── java/com/example/spring_security_jwt/
│   │   │   ├── config/              # Security and JWT configurations
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   ├── entity/              # JPA entities
│   │   │   ├── repository/          # Spring Data JPA repositories
│   │   │   ├── service/             # Business logic
│   │   │   └── SpringSecurityJwtApplication.java
│   │   └── resources/
│   │       └── application.properties
├── pom.xml
└── README.md

Setup Instructions

Clone the Repository (if hosted on Git):
git clone <repository-url>
cd spring-security-jwt


Configure MySQL Database:

Ensure MySQL is running.
Create a database named spring_jwt_db:CREATE DATABASE spring_jwt_db;


Update src/main/resources/application.properties with your MySQL credentials:spring.datasource.url=jdbc:mysql://localhost:3306/spring_jwt_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
jwt.secret=<your-secure-key>
jwt.expiration=86400000


Generate a secure JWT secret key:openssl rand -base64 32

Replace <your-secure-key> with the generated key.


Build the Project:
./mvnw clean install


Run the Application:
./mvnw spring-boot:run

The application will start on http://localhost:8080.


API Endpoints
Use curl, Postman, or any HTTP client to test the endpoints.

Register a User:
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{"username":"testuser","password":"password","role":"USER"}'

Response: User Registered Successfull

Login:
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"username":"testuser","password":"password"}'

Response: {"token":"eyJhbGciOiJIUzUxMiJ9..."}

Access User Endpoint (requires USER or ADMIN role):
curl -X GET http://localhost:8080/api/user/hello \
-H "Authorization: Bearer <your-jwt-token>"

Response: Hello, User

Access Admin Endpoint (requires ADMIN role):
curl -X GET http://localhost:8080/api/admin/hello \
-H "Authorization: Bearer <your-jwt-token>"

Response: Hello, Admin