# Task Manager REST API

A simple REST API for managing tasks, built with Spring Boot. The application stores tasks in memory and provides basic CRUD operations.

## Features

- `GET /tasks` – Retrieve all tasks
- `POST /tasks` – Create a new task
- `GET /tasks/{id}` – Retrieve a single task by ID
- `PUT /tasks/{id}` – Update an existing task
- `DELETE /tasks/{id}` – Delete a task

All endpoints return proper HTTP status codes:
- `200 OK` for successful retrieval/update
- `201 Created` for successful creation
- `204 No Content` for successful deletion
- `400 Bad Request` for invalid input
- `404 Not Found` when task doesn't exist

## Technologies Used

- Java 17
- Spring Boot 3.3.4
- Spring Web
- Spring Validation

## How to Run

1. Clone the repository.
2. Make sure you have JDK 17+ and Maven installed.
3. Navigate to the project directory.
4. Run:
   ```bash
   mvn spring-boot:run
