# Device Notes API

A Spring Boot REST API to create and fetch notes for devices. Built with PostgreSQL, Liquibase migrations, and clean logging.

---

## Features

* Create notes per device
* List device notes with limit
* Input validation with clear errors
* Liquibase DB migrations
* PostgreSQL integration
* Minimal meaningful logging

---

## Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Liquibase
* Maven
* Lombok

---

## Project Structure

```
src/main/java/com/techconsonance/notes
│
├── controllers
├── services
├── repository
├── entity
├── dto
├── exception
└── NotesApplication.java

src/main/resources
└── db/changelog/db.changelog-master.xml
```

---

## ⚙️ Setup Instructions

### Clone Project

```bash
git clone <repo-url>
cd notes
```

---

### Configure Database

Edit `application.yml`

```yaml
spring:
  datasource:
    url: jdbc:postgresql://HOST:PORT/DBNAME
    username: USER
    password: PASSWORD
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true

  liquibase:
    change-log: classpath:db/changelog/db.changelog-master.xml
    enabled: true
```

---

### Run Application

```bash
mvn spring-boot:run
```

Server starts at:

```
http://localhost:8080
```

---

## 📡 API Endpoints

### ➤ Create Note

**POST** `/api/v1/devices/{deviceId}/notes`

Headers:

```
X-User: Nitin
Content-Type: application/json
```

Body:

```json
{
  "note": "Device overheating issue observed"
}
```

Response:

```json
{
  "id": 1,
  "deviceId": 101,
  "note": "Device overheating issue observed",
  "createdBy": "Nitin",
  "createdAt": "2026-02-24T20:00:00"
}
```

---

### ➤ List Notes

**GET** `/api/v1/devices/{deviceId}/notes?limit=20`

Response:

```json
[
  {
    "id": 1,
    "deviceId": 101,
    "note": "Battery replaced",
    "createdBy": "Nitin",
    "createdAt": "2026-02-24T19:00:00"
  }
]
```

---

## Testing with Postman

1. Open Postman
2. Create new request
3. Set method POST
4. URL → `http://localhost:8080/api/v1/devices/101/notes`
5. Add Header → `X-User`
6. Add JSON body
7. Click Send

---

## Logging

### Controller Logs

```
INFO Incoming create note request deviceId=101 user=Nitin
```

### Service Logs

```
INFO Note created successfully noteId=5 deviceId=101
```

### Validation Logs

```
WARN Missing X-User header for deviceId=101
```

---

## Common Errors

| Error                         | Reason             | Fix                                     |
| ----------------------------- | ------------------ | --------------------------------------- |
| Liquibase changelog not found | Wrong path         | Ensure file in `resources/db/changelog` |
| DB connection failed          | Wrong URL/password | Check credentials                       |
| Missing X-User                | Header not sent    | Add header in Postman                   |
| Limit error                   | limit <1 or >100   | Use valid limit                         |

---




