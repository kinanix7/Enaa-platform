# ENAA Skills Tracking Platform

This is a multi-module Maven project implementing a microservices architecture for the ENAA Skills Tracking Platform. It consists of a parent POM managing four core microservices: `competence-service`, `brief-service`, `apprenant-service`, and `validation-service`.

## Project Structure

```
enaa-platform/
├── pom.xml (Parent POM)
├── competence-service/
│   ├── pom.xml
│   └── src/
├── brief-service/
│   ├── pom.xml
│   └── src/
├── apprenant-service/
│   ├── pom.xml
│   └── src/
└── validation-service/
    ├── pom.xml
    └── src/
```

## Technical Specifications

*   **Backend**: Spring Boot, Spring Data JPA, Lombok, Spring WebFlux (for WebClient)
*   **Database**: MySQL
*   **API Documentation**: Swagger / OpenAPI 3

## Setup and Running the Project

### 1. Prerequisites

*   Java 17 or higher
*   Maven
*   MySQL Server
*   IntelliJ IDEA (recommended IDE)

### 2. Database Setup

Create the following MySQL databases:

```sql
CREATE DATABASE competence_db;
CREATE DATABASE brief_db;
CREATE DATABASE apprenant_db;
CREATE DATABASE validation_db;
```

Ensure your MySQL server is running and that the `root` user has no password, or update the `spring.datasource.password` in each service's `src/main/resources/application.properties` file accordingly.

### 3. Build the Project

Navigate to the root `enaa-platform` directory in your terminal and run:

```bash
mvn clean install
```

This will build all modules and download necessary dependencies.

### 4. Run Each Microservice

Each microservice is a Spring Boot application. You can run them individually from your IDE (e.g., IntelliJ IDEA) or via the command line.

**From IntelliJ IDEA:**

For each service, navigate to its main application class (e.g., `CompetenceServiceApplication.java`), right-click, and select "Run 'Application.main()'".

**From Command Line (after building):**

```bash
java -jar competence-service/target/competence-service-1.0.0-SNAPSHOT.jar
java -jar brief-service/target/brief-service-1.0.0-SNAPSHOT.jar
java -jar apprenant-service/target/apprenant-service-1.0.0-SNAPSHOT.jar
java -jar validation-service/target/validation-service-1.0.0-SNAPSHOT.jar
```

Ensure all services are running before testing inter-service communication.

## API Endpoints for Testing

Swagger UI for each service can be accessed at `http://localhost:[port]/swagger-ui.html`.

### 1. Competence Service (Port: 8081)

**Base URL**: `http://localhost:8081/api/competences`

*   **GET /api/competences**
    *   Description: Get all competencies.
    *   Example: `GET http://localhost:8081/api/competences`
*   **GET /api/competences?ids={id1},{id2}**
    *   Description: Get competencies by a list of IDs.
    *   Example: `GET http://localhost:8081/api/competences?ids=1,2`
*   **POST /api/competences**
    *   Description: Create a new competence.
    *   Body (JSON): `{"code": "C001", "titre": "Programming", "description": "Ability to write code"}`
*   **GET /api/competences/{id}**
    *   Description: Get a competence by ID.
    *   Example: `GET http://localhost:8081/api/competences/1`
*   **PUT /api/competences/{id}**
    *   Description: Update an existing competence.
    *   Body (JSON): `{"code": "C001", "titre": "Advanced Programming", "description": "Ability to write complex code"}`
*   **DELETE /api/competences/{id}**
    *   Description: Delete a competence by ID.
    *   Example: `DELETE http://localhost:8081/api/competences/1`
*   **POST /api/competences/{competenceId}/sous-competences**
    *   Description: Add a sub-competence to a competence.
    *   Example: `POST http://localhost:8081/api/competences/1/sous-competences`
    *   Body (JSON): `{"titre": "Java Basics"}`
*   **PUT /api/sous-competences/{id}**
    *   Description: Update a sub-competence.
    *   Example: `PUT http://localhost:8081/api/sous-competences/1`
    *   Body (JSON):
        (Copyable JSON below)
        ```json
        {"titre": "Advanced Java"}
        ```
*   **DELETE /api/sous-competences/{id}**
    *   Description: Delete a sub-competence.
    *   Example: `DELETE http://localhost:8081/api/sous-competences/1`

### 2. Brief Service (Port: 8082)

**Base URL**: `http://localhost:8082/api/briefs`

*   **GET /api/briefs**
    *   Description: Get all briefs.
*   **GET /api/briefs?ids={id1},{id2}**
    *   Description: Get briefs by a list of IDs.
    *   Example: `GET http://localhost:8082/api/briefs?ids=1,2`
*   **POST /api/briefs**
    *   Description: Create a new brief.
    *   Body (JSON): `{"titre": "Project Alpha", "description": "Develop a new web application"}`
*   **GET /api/briefs/{id}**
    *   Description: Get a brief by ID, including associated competencies.
    *   Example: `GET http://localhost:8082/api/briefs/1`
*   **PUT /api/briefs/{id}**
    *   Description: Update an existing brief.
    *   Body (JSON): `{"titre": "Project Beta", "description": "Refactor existing code"}`
*   **DELETE /api/briefs/{id}**
    *   Description: Delete a brief by ID.
*   **POST /api/briefs/{briefId}/competences**
    *   Description: Link a competence to a brief.
    *   Example: `POST http://localhost:8082/api/briefs/1/competences`
    *   Body (JSON):
        (Copyable JSON below)
        ```json
        1
        ```
*   **DELETE /api/briefs/{briefId}/competences/{competenceId}**
    *   Description: Unlink a competence from a brief.
    *   Example: `DELETE http://localhost:8082/api/briefs/1/competences/1`

### 3. Apprenant Service (Port: 8083)

**Base URL**: `http://localhost:8083/api/apprenants`

*   **GET /api/apprenants**
    *   Description: Get all learners.
*   **POST /api/apprenants**
    *   Description: Create a new learner.
    *   Body (JSON): `{"prenom": "John", "nom": "Doe", "email": "john.doe@example.com"}`
*   **GET /api/apprenants/{id}**
    *   Description: Get a learner by ID.
*   **PUT /api/apprenants/{id}**
    *   Description: Update an existing learner.
    *   Body (JSON): `{"prenom": "Jane", "nom": "Doe", "email": "jane.doe@example.com"}`
*   **GET /api/apprenants/{apprenantId}/rendus**
    *   Description: Get all submissions (rendus) for a specific learner.
*   **POST /api/rendus**
    *   Description: Create a new submission (rendu).
    *   Body (JSON):
        (Copyable JSON below)
        ```json
        {"url": "http://example.com/submission1", "dateSoumission": "2025-07-17T10:00:00.000+00:00", "apprenantId": 1, "briefId": 1}
        ```
*   **GET /api/rendus/{id}**
    *   Description: Get a submission (rendu) by ID.

### 4. Validation Service (Port: 8084)

**Base URL**: `http://localhost:8084/api/validations`

*   **GET /api/validations**
    *   Description: Get all validations.
*   **POST /api/validations**
    *   Description: Create a new validation.
    *   Body (JSON): `{"apprenantId": 1, "competenceId": 1, "briefId": 1, "statut": "VALIDE"}`
*   **PUT /api/validations/{id}**
    *   Description: Update an existing validation.
    *   Body (JSON):
        (Copyable JSON below)
        ```json
        {"statut": "NON_VALIDE"}
        ```
*   **GET /api/apprenants/{apprenantId}/dashboard**
    *   Description: Get a dashboard view for a learner, aggregating validation, competence, and brief details.
    *   Example: `GET http://localhost:8084/api/apprenants/1/dashboard`
