# Library Management System

A command-line Library Management System built with Java 17, Maven and Java file persistence.

## 1. Project Overview

This application manages books, library members, book issue/return transactions, overdue fines and administrative reports. It is designed as a self-contained command-line project so an evaluator can build and run it from a terminal without a GUI.

## 2. Functional Modules

1. **Authentication** – administrator login.
2. **Book Management** – add, update, delete, search and list books.
3. **Member Management** – register, update, delete, search and list members.
4. **Issue & Return Management** – issue books, return books, track due dates and calculate fines.
5. **Reports & Analytics** – inventory, active loans, overdue loans and fine totals.

## 3. Technology

- Java 17+
- Maven
- SQLite
- JDBC
- JUnit 5
- Git/GitHub

The project uses Java serialization for local persistence, so it requires no separate database server or external runtime database installation.

## 4. Requirements

Install:
- JDK 17 or newer
- Maven 3.8+ (or use the Maven wrapper if you add one)

Check:
```bash
java -version
mvn -version
```

## 5. Run

From the project root:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass=com.library.Main
```

A simpler Maven command can also be used after adding the exec plugin to your local Maven configuration, but the source project itself does not depend on a GUI.

## 6. Default Login

```text
Username: admin
Password: admin123
```


## 7. Database

On first run, the application creates:

```text
data/library.db (Java-serialized local store)
```

The schema is also provided in:

```text
database/library.sql
```

The application automatically creates the tables and inserts sample books/members if the database is empty.

## 8. Test

```bash
mvn test



The test suite includes fine-calculation and validation tests.

## 9. Project Structure

```text
src/main/java/com/library/
├── Main.java
├── database/DatabaseConnection.java
├── model/
│   ├── Book.java
│   ├── Member.java
│   ├── IssueRecord.java
│   └── User.java
├── dao/
│   ├── BookDAO.java
│   ├── MemberDAO.java
│   └── IssueDAO.java
├── service/
│   ├── AuthService.java
│   ├── BookService.java
│   ├── MemberService.java
│   ├── IssueService.java
│   └── ReportService.java
└── util/
    ├── InputValidator.java
    └── FineCalculator.java
```

## 10. Workflow

Login → Main Menu → select a module → validate input → service layer → DAO/database → display result.

## 11. Non-Functional Requirements

- **Performance:** indexed lookups and lightweight SQLite storage.
- **Security:** authentication is required for administrative operations.
- **Reliability:** database operations use transactions where appropriate and validate user input.
- **Maintainability:** model/DAO/service/util layers separate responsibilities.
- **Error handling:** invalid input and database errors are reported without crashing the main menu.
- **Resource efficiency:** JDBC resources are closed with try-with-resources.
