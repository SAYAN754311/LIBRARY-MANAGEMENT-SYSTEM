# Library Management System

A **Java command-line Library Management System** developed as a modular academic project. The application provides administrator authentication, book and member management, issue/return processing, overdue-fine calculation, and library reports.

The project is intentionally self-contained and uses **Java serialization for local file persistence**, so it can be compiled and executed without installing a separate database server or downloading a JDBC driver.

---

## 1. Project Overview

The Library Management System is designed to replace basic manual library-record activities with a structured command-line application.

The system maintains:

- Book catalogue and available-copy information
- Library member records
- Book issue and return transactions
- Issue and due dates
- Overdue fine calculations
- Active-loan information
- Inventory and summary reports
- Administrator authentication

The application follows a layered structure so that user interaction, business rules, persistence, and utility functions remain separated.

---

## 2. Main Objectives

The project aims to:

1. Provide a simple and reliable way to manage library books.
2. Maintain member information in a structured format.
3. Record book issue and return transactions.
4. Automatically determine due dates for issued books.
5. Calculate overdue fines consistently.
6. Provide useful inventory and transaction summaries.
7. Demonstrate modular Java programming using model, DAO, service, and utility layers.
8. Provide a project that can be executed directly from the command line.

---

## 3. Functional Modules

### Module 1 — Authentication

- Administrator login
- Default demonstration credentials
- Prevents access to management functions until login succeeds

### Module 2 — Book Management

- Add a new book
- Update existing book details
- Delete a book
- Search for books
- List all books
- Track total and available copies

### Module 3 — Member Management

- Register members
- Update member information
- Delete members
- Search members
- List all members

### Module 4 — Issue and Return Management

- Issue an available book to a registered member
- Automatically record the issue date
- Calculate the due date
- Display active loans
- Return an issued book
- Calculate an overdue fine when applicable
- Restore the returned copy to available inventory

### Module 5 — Reports

- Total number of book titles
- Total number of physical copies
- Number of currently available copies
- Total registered members
- Fine summary
- Active-loan information

---

## 4. Technology Stack

| Technology | Purpose |
|---|---|
| Java 17+ | Application development |
| Maven | Project/build configuration |
| Java Serialization | Local persistent data storage |
| Git | Version control |
| GitHub | Source-code repository and submission |


---

## 5. System Requirements

### Minimum software requirements

- JDK 17 or newer
- Maven 3.8+ (recommended for standard Maven workflow)
- Git, if the project is being uploaded to GitHub

### Verify Java

```bash
java -version
javac -version
```

### Verify Maven

```bash
mvn -version
```



## 6. Project Setup

Clone the repository and move into the project directory:

```bash
git clone <https://github.com/SAYAN754311/LIBRARY-MANAGEMENT-SYSTEM.git>
cd LibraryManagementSystem
```



---

## 7. Build and Run with Java Directly

This is the most dependency-independent method.

### Step 1 — Compile the application

From the project root:

```bash
rm -rf out
mkdir -p out
javac -d out $(find src/main/java -name '*.java')
```

### Step 2 — Run the application

```bash
java -cp out com.library.Main
```

### Windows PowerShell alternative

```powershell
Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue
New-Item -ItemType Directory out
javac -d out (Get-ChildItem -Recurse src/main/java -Filter *.java).FullName
java -cp out com.library.Main
```

---

## 8. Build and Run with Maven

If Maven is installed:

```bash
mvn clean compile
```

Then compile the application and run the main class using the generated classes:

```bash
java -cp target/classes com.library.Main
```

The project does not require a third-party runtime database driver.

---

## 9. Running the Smoke Test

A dependency-free executable smoke test is included for the fine-calculation utility.

Compile the application and test class:

```bash
rm -rf out
mkdir -p out
javac -d out $(find src/main/java -name '*.java') src/test/java/com/library/FineCalculatorTest.java
```

Run the test:

```bash
java -cp out com.library.FineCalculatorTest
```

Expected result:

```text
PASS: on-time
PASS: late
PASS: early
All tests passed.
```

The test verifies that:

- an on-time return produces zero fine;
- a late return produces the expected fine;
- an early return does not produce a negative fine.

---

## 10. Default Login

The application contains demonstration administrator credentials:

```text
Username: admin
Password: admin123


## 11. Sample Data

On first initialization, the application creates demonstration data so the main modules can be tested immediately.

### Default books

| ID | Title | Author | Category | Copies |
|---:|---|---|---|---:|
| 1 | Effective Java | Joshua Bloch | Programming | 3 |
| 2 | Clean Code | Robert C. Martin | Programming | 2 |
| 3 | Designing Data-Intensive Applications | Martin Kleppmann | Technology | 2 |

### Default members

| ID | Name |
|---:|---|
| 1 | Demo Student |
| 2 | Demo Faculty |

The sample records make it possible to demonstrate searching, issuing books, viewing active loans, and generating reports without manually creating every record first.

---

## 12. Data Persistence

The application stores its local data in:

```text
data/library.db
```

Despite the `.db` filename, this is **not an SQLite database**. It is a Java-serialized local store managed by `DatabaseConnection`.

The application creates/initializes the data store when required and seeds demonstration records when the store is empty.

### Resetting the demonstration data

To start with a fresh data store, stop the application and remove:

```text
data/library.db
```

Then run the application again. The initial sample data will be created again.

> Do not delete the file if you need to preserve changes made during your own demonstration.

---

## 13. Application Workflow

The high-level workflow is:

```text
Start Application
       |
       v
Administrator Login
       |
       v
    Main Menu
       |
       +----> Book Management
       |
       +----> Member Management
       |
       +----> Issue / Return
       |
       +----> Reports
       |
       v
     Exit
```

For an operation such as issuing a book:

```text
User Input
   |
   v
Input Validation
   |
   v
Service Layer
   |
   v
DAO / Persistence Layer
   |
   v
Local Data Store
   |
   v
Result displayed in CLI
```

---

## 14. Project Architecture

The source code is organized into layers:

### Model layer

Contains the application's data objects:

- `Book`
- `Member`
- `IssueRecord`
- `User`

### DAO layer

Provides persistence operations for:

- books;
- members;
- issue records.

Classes:

- `BookDAO`
- `MemberDAO`
- `IssueDAO`

### Service layer

Contains business operations and validation flow:

- `AuthService`
- `BookService`
- `MemberService`
- `IssueService`
- `ReportService`

### Utility layer

Contains reusable application logic:

- `InputValidator`
- `FineCalculator`

### Database/persistence layer

`DatabaseConnection` manages the application's local serialized data store.

### Main application

`Main.java` provides the command-line menu, user interaction, and module navigation.

## 15. Error Handling and Validation

The application validates user input before performing operations. Examples include:

- required text fields;
- numeric identifiers;
- book/member existence;
- available-copy checks before issuing a book;
- active-loan checks during return operations;
- date-based fine calculation.

Errors are displayed in the command line so the application can continue running instead of terminating after normal user-input mistakes.

---

## 16. Fine Calculation Rules

The fine calculation is handled by `FineCalculator`.

The basic rule is:

```text
If return date <= due date:
    Fine = 0

If return date > due date:
    Fine = number of overdue days × daily fine rate
```

The included smoke test uses a daily rate of `5` for its late-return example.

## 18. Documentation

Additional documentation is available in the `docs/` directory:

- Use-case diagram
- Workflow diagram
- Class diagram
- ER-style data diagram
- Sequence diagram
- Report template

These documents explain the system from functional, structural, and interaction perspectives.

---

## 19. Known Limitations

This is an academic command-line application rather than a production library platform. Current limitations include:

- single local data store;
- administrator-only authentication;
- no web or mobile interface;
- no online payment processing;
- no email/SMS notifications;
- no barcode scanner integration;
- no cloud synchronization;
- demonstration authentication credentials are stored for project use rather than production security;
- Java serialization is intended for this local academic application, not for a multi-user production database.

---

## 20. Possible Future Enhancements

The system could be extended with:

1. A graphical or web-based interface.
2. Role-based access for administrators, librarians, faculty, and students.
3. A production relational database such as PostgreSQL or MySQL.
4. Password hashing and stronger authentication.
5. Book-cover/image support.
6. Barcode or QR-code scanning.
7. Email/SMS due-date reminders.
8. Advanced search and filtering.
9. Export of reports to CSV/PDF.
10. Multi-user and network access.
11. Automated integration and unit testing with a test framework.

---



mkdir -p out
javac -d out $(find src/main/java -name '*.java')
java -cp out com.library.FineCalculatorTest
java -cp out com.library.Main
```

Expected smoke-test ending:

```text
PASS: on-time
PASS: late
PASS: early
All tests passed.
```

The first application launch creates `data/library.db` with demonstration users, books, and members. Because that runtime file is ignored by Git, every reviewer gets a clean local data store.
