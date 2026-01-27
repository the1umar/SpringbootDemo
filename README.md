# Student Management API

A RESTful API for managing student records built with Spring Boot, PostgreSQL, and JPA/Hibernate. This project demonstrates the classic 3-layer architecture pattern commonly used in enterprise Java applications.

## 📋 Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [API Endpoints](#api-endpoints)
- [Project Structure](#project-structure)
- [Database Configuration](#database-configuration)
- [Running the Application](#running-the-application)
- [Testing the API](#testing-the-api)

## ✨ Features

- **CRUD Operations**: Create, Read, Update, and Delete student records
- **Email Validation**: Prevents duplicate student email addresses
- **Automatic Age Calculation**: Age is computed dynamically from date of birth
- **RESTful Design**: Follows REST principles and HTTP conventions
- **Persistent Storage**: Data persists between application restarts
- **Initial Data Seeding**: Automatically populates database with sample students on first run

## 🏗 Architecture

This application follows the **3-Layer Architecture** pattern:

```
┌─────────────────────────────────────────────────────────────┐
│                         CLIENT                               │
│                    (Browser/Postman)                         │
└────────────────────────────┬────────────────────────────────┘
                             │ HTTP Requests
                             ↓
┌─────────────────────────────────────────────────────────────┐
│                   CONTROLLER LAYER                           │
│              (StudentController.java)                        │
│                                                              │
│  • Handles HTTP requests (GET, POST, PUT, DELETE)           │
│  • Maps URLs to methods                                     │
│  • Validates input and returns responses                    │
└────────────────────────────┬────────────────────────────────┘
                             │
                             ↓
┌─────────────────────────────────────────────────────────────┐
│                    SERVICE LAYER                             │
│              (StudentService.java)                           │
│                                                              │
│  • Contains business logic                                  │
│  • Validates email uniqueness                               │
│  • Manages transactions                                     │
│  • Orchestrates data operations                             │
└────────────────────────────┬────────────────────────────────┘
                             │
                             ↓
┌─────────────────────────────────────────────────────────────┐
│                   REPOSITORY LAYER                           │
│             (StudentRepository.java)                         │
│                                                              │
│  • Data access interface                                    │
│  • Extends JpaRepository                                    │
│  • Provides database CRUD operations                        │
│  • Custom query methods                                     │
└────────────────────────────┬────────────────────────────────┘
                             │
                             ↓
┌─────────────────────────────────────────────────────────────┐
│                      DATABASE                                │
│                   PostgreSQL                                 │
│                                                              │
│  • Stores student records                                   │
│  • Manages sequences for ID generation                      │
└─────────────────────────────────────────────────────────────┘
```

### Request Flow Example

```
GET /api/v1/student
    ↓
StudentController.getStudents()
    ↓
StudentService.getStudents()
    ↓
StudentRepository.findAll()
    ↓
JPA/Hibernate queries PostgreSQL
    ↓
Database returns rows
    ↓
JPA converts to Student objects
    ↓
Returns as JSON to client
```

## 🛠 Technologies Used

- **Java 21** - Programming language
- **Spring Boot 4.0.1** - Application framework
- **Spring Data JPA** - Database abstraction layer
- **Hibernate 7.2.0** - ORM (Object-Relational Mapping)
- **PostgreSQL** - Relational database
- **Maven** - Dependency management and build tool
- **Jakarta Persistence API** - JPA specification

## 📦 Prerequisites

Before running this application, ensure you have the following installed:

1. **Java Development Kit (JDK) 21 or higher**
   ```bash
   java -version
   ```

2. **Maven 3.6+**
   ```bash
   mvn -version
   ```

3. **PostgreSQL 12+**
   ```bash
   psql --version
   ```

4. **Git** (for cloning the repository)
   ```bash
   git --version
   ```

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd demo
```

### 2. Set Up PostgreSQL Database

**Option A: Using PostgreSQL Command Line**

```bash
# Login to PostgreSQL
psql -U postgres

# Create the database
CREATE DATABASE student;

# Exit
\q
```

**Option B: Using pgAdmin**
- Open pgAdmin
- Right-click on "Databases"
- Select "Create" → "Database"
- Name it "student"
- Click "Save"

### 3. Configure Database Credentials

Open `src/main/resources/application.properties` and update:

```properties
spring.datasource.username=YOUR_POSTGRES_USERNAME
spring.datasource.password=YOUR_POSTGRES_PASSWORD
```

**Example:**
```properties
spring.datasource.username=postgres
spring.datasource.password=mypassword123
```

### 4. Build the Project

```bash
mvn clean install
```

This will:
- Download all dependencies
- Compile the code
- Run tests (if any)
- Package the application

## ▶️ Running the Application

### Using Maven

```bash
mvn spring-boot:run
```

### Using Java

```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

### Using IDE (IntelliJ IDEA / Eclipse)

1. Open the project
2. Navigate to `src/main/java/com/example/demo/DemoApplication.java`
3. Right-click and select "Run 'DemoApplication'"

**Expected Output:**

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v4.0.1)

...
Tomcat started on port 8080 (http)
Started DemoApplication in 2.5 seconds
```

The application will be available at: **http://localhost:8080**

## 📡 API Endpoints

### Base URL
```
http://localhost:8080/api/v1/student
```

### 1. Get All Students

**Request:**
```http
GET /api/v1/student
```

**Response:**
```json
[
  {
    "id": 1,
    "name": "Mariam",
    "email": "mariam.jamal@gmail.com",
    "dob": "2000-01-05",
    "age": 26
  },
  {
    "id": 2,
    "name": "Alex",
    "email": "alex@gmail.com",
    "dob": "2004-01-05",
    "age": 22
  }
]
```

### 2. Create a New Student

**Request:**
```http
POST /api/v1/student
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@gmail.com",
  "dob": "1999-05-15"
}
```

**Response:**
```
201 Created
```

**Error Response (Duplicate Email):**
```json
{
  "timestamp": "2026-01-27T01:30:00.000Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Student email already exists"
}
```

### 3. Update a Student

**Request:**
```http
PUT /api/v1/student/1?name=Jane&email=jane@gmail.com
```

**Query Parameters:**
- `name` (optional) - New name for the student
- `email` (optional) - New email for the student

**Examples:**
```http
# Update only name
PUT /api/v1/student/1?name=Jane

# Update only email
PUT /api/v1/student/1?email=jane@gmail.com

# Update both
PUT /api/v1/student/1?name=Jane&email=jane@gmail.com
```

**Response:**
```
200 OK
```

### 4. Delete a Student

**Request:**
```http
DELETE /api/v1/student/1
```

**Response:**
```
200 OK
```

**Error Response (Student Not Found):**
```json
{
  "timestamp": "2026-01-27T01:30:00.000Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Student with id 1 does not exist"
}
```

## 📁 Project Structure

```
demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── demo/
│   │   │               ├── DemoApplication.java       # Main application entry point
│   │   │               └── student/
│   │   │                   ├── Student.java           # Entity class (database model)
│   │   │                   ├── StudentController.java # REST API endpoints
│   │   │                   ├── StudentService.java    # Business logic
│   │   │                   ├── StudentRepository.java # Data access layer
│   │   │                   └── StudentConfig.java     # Initial data seeding
│   │   └── resources/
│   │       └── application.properties                 # Configuration file
│   └── test/
│       └── java/
├── target/                                            # Compiled files (generated)
├── pom.xml                                            # Maven configuration
└── README.md                                          # This file
```

### File Descriptions

**DemoApplication.java**
- Main entry point of the Spring Boot application
- Contains the `main()` method that starts the application

**Student.java**
- JPA Entity representing the `student` table
- Contains fields: id, name, email, dob
- Age is calculated dynamically from date of birth using `@Transient`

**StudentController.java**
- REST controller handling HTTP requests
- Maps endpoints to service methods
- Handles request/response formatting

**StudentService.java**
- Contains business logic
- Validates data before database operations
- Manages transactions with `@Transactional`

**StudentRepository.java**
- Interface extending `JpaRepository`
- Provides CRUD operations automatically
- Contains custom query method `findStudentByEmail()`

**StudentConfig.java**
- Configuration class for initial data seeding
- Runs once at startup to populate database with sample students
- Only seeds if database is empty

## 💾 Database Configuration

### application.properties Explained

```properties
# Database connection URL
spring.datasource.url=jdbc:postgresql://localhost:5432/student

# PostgreSQL credentials
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

# Schema management strategy
# Options: create-drop, create, update, validate, none
spring.jpa.hibernate.ddl-auto=update

# Show SQL queries in console (for debugging)
spring.jpa.show-sql=true

# PostgreSQL dialect
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Format SQL queries for readability
spring.jpa.properties.hibernate.format_sql=true

# Application name
spring.application.name=demo

# Include error messages in responses
spring.web.error.include-message=always
```

### Database Schema

The application automatically creates the following table:

```sql
CREATE TABLE student (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    dob DATE
);

CREATE SEQUENCE student_sequence START WITH 1 INCREMENT BY 1;
```

**Note:** The `age` field is not stored in the database. It's calculated dynamically from the `dob` field.

## 🧪 Testing the API

### Using cURL

**Get all students:**
```bash
curl http://localhost:8080/api/v1/student
```

**Create a student:**
```bash
curl -X POST http://localhost:8080/api/v1/student \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test User",
    "email": "test@example.com",
    "dob": "1995-03-20"
  }'
```

**Update a student:**
```bash
curl -X PUT "http://localhost:8080/api/v1/student/1?name=UpdatedName&email=updated@example.com"
```

**Delete a student:**
```bash
curl -X DELETE http://localhost:8080/api/v1/student/1
```

### Using Postman

1. **Download Postman**: https://www.postman.com/downloads/
2. **Import Collection**: Create a new collection called "Student API"
3. **Add Requests**: Create requests for each endpoint listed above
4. **Test**: Execute requests and verify responses

### Using Browser (GET only)

Simply navigate to:
```
http://localhost:8080/api/v1/student
```

## 🔧 Configuration Options

### Change Server Port

Add to `application.properties`:
```properties
server.port=9090
```

### Change Database

To use a different database (e.g., MySQL):

1. Update `pom.xml` with MySQL driver
2. Change datasource URL:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/student
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
   ```

### Disable Data Seeding

In `StudentConfig.java`, comment out the `@Bean` method or set a profile condition.

## 🐛 Troubleshooting

### Common Issues

**1. "Port 8080 already in use"**

Change the port in `application.properties`:
```properties
server.port=8081
```

**2. "Connection refused to localhost:5432"**

- Ensure PostgreSQL is running
- Check if the port is correct
- Verify firewall settings

**3. "Database 'student' does not exist"**

Create the database:
```bash
psql -U postgres
CREATE DATABASE student;
\q
```

**4. "Authentication failed for user"**

- Verify username and password in `application.properties`
- Check PostgreSQL `pg_hba.conf` for authentication method

**5. Application starts but endpoints return 404**

- Verify the base URL: `http://localhost:8080/api/v1/student`
- Check that all controllers have `@RestController` annotation
- Ensure the controller is in the correct package

## 📚 Learning Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [REST API Best Practices](https://restfulapi.net/)

## 📝 Future Enhancements

Potential improvements for this project:

- [ ] Add pagination for GET all students endpoint
- [ ] Implement search/filter functionality
- [ ] Add authentication and authorization (Spring Security)
- [ ] Create unit and integration tests
- [ ] Add API documentation (Swagger/OpenAPI)
- [ ] Implement DTO (Data Transfer Objects) pattern
- [ ] Add logging with SLF4J
- [ ] Create custom exception handlers
- [ ] Add input validation with Jakarta Bean Validation
- [ ] Implement database migrations (Flyway/Liquibase)

## 👨‍💻 Author

Built as a learning project to understand Spring Boot fundamentals and RESTful API development.

## 📄 License

This project is open source and available for educational purposes.

---

**Happy Coding! 🚀**