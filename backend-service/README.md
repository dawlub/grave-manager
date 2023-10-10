# Backend Service for Grave Manager

Handles the data, API calls, and business logic for the Grave Manager.

## Technology stack
- Language: **Java 17**
- DI framework: **Spring Boot 3**
- Build tool: **Gradle**
- Database: **PostgreSQL 16**
- Auto code generator: **Lombok**
- Unit tests: **Junit 5 Jupiter**
- API: **swagger OpenApi**

## Features

- **Grave Registration**: Add new graves with associated details.
- **Search Functionality**: Retrieve graves based on criteria.
- **Maintenance Logs**: Record maintenance activities.
- **Notifications & Reminders**: Set and manage notifications.

## Swagger

More detailed API description you can find at swagger once you run the application.

- [localhost swagger](http://localhost:8080/swagger-ui/index.html)

## Run requirements

- Java 17 or newer
- PostgresDB

Application local profile be default require following db configuration:
- host-port: localhost:5432
- db-name: grave-manager-db
- username: postgres
- password: password

To run db with mentioned setup please visit [parent folder README.md](../README.md##Run-infrastructure-locally)

## How to run service locally

1. Navigate to the backend service directory:
```shell
cd backend-service
```
2. Build the project:
```shell
./gradlew clean build
```
Mention above command doesn't include integration tests.
If you want run build and integration tests in one command use:
```shell
./gradlew clean build testIntegration
```
3. To run application from terminal:
```shell
java -jar -Dspring.profiles.active=local build/libs/backend-*.jar
```

Alternatively you can use IDE tools to run application with the appropriate profile.