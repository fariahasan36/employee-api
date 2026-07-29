# Employee API

Employee REST API built with **Java 25 and Spring Boot 4.1**.

## Tech Stack

* Java 25
* Spring Boot 4.1
* Spring Data JPA
* Spring Security
* JWT
* H2 Database
* Maven
* Docker

## Features

* Create an employee
* Get all employees
* Get Employee By Id
* Get Employee By Name
* Update employee By Name
* Delete Employee By Id
* JWT authentication
* H2 database
* Docker support

## Run the Project

Clone the project:

```bash
git clone https://github.com/fariahasan36/employee-api.git
cd employee-api
```

Run the application:

```bash
.\mvnw spring-boot:run
```

The API runs on:

```text
http://localhost:8080
```

## Authentication

First generate a JWT:

```http
POST /auth/token
```

Use the returned token when accessing protected employee endpoints:

```http
Authorization: Bearer YOUR_TOKEN
```

## Employee API

### Create Employee

```http
POST /api/employees
```

Example Request:

```json
{
  "name": "Faria",
  "email": "faria@example.com"
  "department": "IT"
}
```

### Get All Employees

```http
GET /api/employees
```

Send the JWT:

```http
Authorization: Bearer YOUR_TOKEN
```

### Get Employee By ID

```http
GET /api/employees/1
```

Send the JWT:

```http
Authorization: Bearer YOUR_TOKEN
```

### Get Employee By Name

```http
GET /api/employees/search?name=Faria
```

Send the JWT:

```http
Authorization: Bearer YOUR_TOKEN
```

### Update Employee By Name

```http
PATCH /api/employees/name/Faria
```

Example Request:

```json
{
  "name": "Faria Updated"
}
```

Send the JWT:

```http
Authorization: Bearer YOUR_TOKEN
```

### Delete Employee By ID

```http
DELETE /api/employees/1
```
Send the JWT:

```http
Authorization: Bearer YOUR_TOKEN
```

## H2 Database

H2 console:

```text
http://localhost:8080/h2-console
```

## Actuator

Application health:

```text
http://localhost:8080/actuator/health
```

Metrics:

```text
http://localhost:8080/actuator/metrics
```

Prometheus metrics:

```text
http://localhost:8080/actuator/prometheus
```

## Docker

Build the Docker image:

```bash
docker build -t employee-api .
```

Run it:

```bash
docker run -p 8080:8080 employee-api
```

The API is then available at:

```text
http://localhost:8080
```

## CI/CD

GitHub Actions is used to:

1. Build the project
2. Run tests
3. Build the Docker image
4. Push the image to Docker Hub
5. Deploy the application to Render

## Author

Faria Hasan Talukder
