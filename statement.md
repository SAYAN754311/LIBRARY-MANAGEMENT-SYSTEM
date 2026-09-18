# Project Statement — Library Management System

## 1. Project Title

**Library Management System**

---

## 2. Problem Statement

Libraries need to maintain accurate information about books, available copies, members, and book transactions. When these activities are maintained manually, it can become difficult to quickly identify available books, locate member information, track issued books, determine due dates, and calculate overdue fines consistently.

The proposed Library Management System provides a simple command-line solution for organizing these activities in one application. It allows an administrator to manage the book catalogue and member records, process book issue and return operations, calculate overdue fines, and view library summaries.

---

## 3. Motivation

The project is intended to demonstrate how a real-world administrative process can be converted into a structured software application using Java.

The application focuses on the core library workflow rather than a graphical interface. This makes the system suitable for demonstrating:

- object-oriented programming;
- modular software design;
- input validation;
- business-rule implementation;
- local data persistence;
- transaction-oriented operations;
- reporting;
- command-line execution and testing.

---

## 4. Aim of the Project

The main aim is to develop a self-contained Java command-line application that simplifies common library management activities and demonstrates a modular approach to application development.

---

## 5. Objectives

The objectives of the project are to:

1. Implement administrator authentication.
2. Maintain a catalogue of library books.
3. Add, update, delete, search, and list book records.
4. Maintain library member information.
5. Add, update, delete, search, and list member records.
6. Issue available books to registered members.
7. Record issue and due dates.
8. Process returned books.
9. Calculate overdue fines using a defined business rule.
10. Display active loans and library summary information.
11. Persist application data locally between executions.
12. Organize the source code into reusable layers.
13. Demonstrate the working system through executable tests and terminal screenshots.

---

## 6. Scope

### Included in the scope

The application supports:

- administrator login;
- book catalogue management;
- member management;
- book issue operations;
- book return operations;
- active-loan tracking;
- due-date handling;
- overdue-fine calculation;
- inventory summaries;
- member counts;
- local persistent storage;
- command-line interaction.

### Outside the current scope

The project does not currently provide:

- a web application;
- a mobile application;
- a graphical desktop interface;
- online payment processing;
- email or SMS notifications;
- barcode/QR scanning;
- cloud synchronization;
- multi-branch library management;
- production-grade role-based access control.

These can be considered future enhancements.

---

## 7. Target Users

The intended users of the current application are:

### Library Administrator / Librarian

The primary user who manages books, members, issues, returns, and reports.

### Academic Project Evaluator

An evaluator can use the command-line menus, sample records, test output, and documentation to verify the implementation.

### Student Developer

The project also serves as an example of modular Java application development and can be extended with additional functionality.

---

## 8. Functional Requirements

### FR-01: Authentication

The system shall require administrator authentication before allowing access to library-management functions.

### FR-02: Book Management

The system shall allow the administrator to add, update, delete, search, and list books.

### FR-03: Member Management

The system shall allow the administrator to register, update, delete, search, and list members.

### FR-04: Book Issue

The system shall allow an available book to be issued to a valid member and shall record the issue and due dates.

### FR-05: Book Return

The system shall allow an active loan to be returned and shall update book availability.

### FR-06: Fine Calculation

The system shall calculate an overdue fine when a book is returned after its due date.

### FR-07: Active Loans

The system shall display currently active book loans with relevant member and due-date information.

### FR-08: Reports

The system shall provide library summary information such as titles, copies, availability, members, and fines.

### FR-09: Persistence

The system shall save application data to a local persistent store so that records can be retained between program executions.

---

## 9. Non-Functional Requirements

### Usability

The menus should be understandable to a user operating the application from a terminal.

### Maintainability

The implementation should separate data models, persistence operations, business services, and utility functions.

### Reliability

Normal invalid-input situations should be handled with user-readable error messages instead of terminating the application unexpectedly.

### Portability

The application should run on systems supporting JDK 17 or later without requiring a separate database server.

### Performance

The project is intended for small academic/demo datasets and uses lightweight local persistence suitable for that scale.

### Security

Administrator authentication is included as a basic access-control mechanism. The default credentials are demonstration credentials and are not intended for production use.

---

## 10. System Modules

```text
Library Management System
│
├── Authentication
│
├── Book Management
│   ├── Add
│   ├── Update
│   ├── Delete
│   ├── Search
│   └── List
│
├── Member Management
│   ├── Add
│   ├── Update
│   ├── Delete
│   ├── Search
│   └── List
│
├── Issue / Return
│   ├── Issue Book
│   ├── Return Book
│   └── Active Loans
│
└── Reports
    └── Library Summary
```

---

## 11. Technology and Implementation

The project is implemented in **Java 17+** and is organized as a command-line application.

The source uses a layered design:

```text
Main / CLI
    ↓
Service Layer
    ↓
DAO Layer
    ↓
Local Persistence
```

The current executable version uses **Java serialization** for local persistence. This means that no external database server or JDBC driver is required to execute the submitted application.

The local data store is created under:

```text
data/library.db
```

The `.db` filename is retained as a project data-file name; the file itself is a Java-serialized store rather than an SQLite database.

---

## 12. Main Classes

### `Main`

Controls the command-line interface, login flow, menus, and navigation between modules.

### `DatabaseConnection`

Loads and saves the local application data store and initializes demonstration data when necessary.

### Model classes

- `Book` — represents a library book and copy information.
- `Member` — represents a registered library member.
- `IssueRecord` — represents an issue/return transaction.
- `User` — represents an application user.

### DAO classes

- `BookDAO` — book persistence operations.
- `MemberDAO` — member persistence operations.
- `IssueDAO` — issue-record persistence operations.

### Service classes

- `AuthService` — authentication logic.
- `BookService` — book-management operations.
- `MemberService` — member-management operations.
- `IssueService` — issue, return, due-date, and fine workflow.
- `ReportService` — summary/report calculations.

### Utility classes

- `InputValidator` — reusable input checks.
- `FineCalculator` — overdue-fine calculation logic.

---

## 13. Business Rules

### Book availability

A book can be issued only when at least one copy is available.

### Issue operation

When a book is issued:

1. The book ID is validated.
2. The member ID is validated.
3. Availability is checked.
4. An issue record is created.
5. The available-copy count is reduced.
6. A due date is recorded.

### Return operation

When a book is returned:

1. The active loan is identified.
2. The return date is recorded.
3. Overdue days are determined.
4. The fine is calculated when applicable.
5. The book's available-copy count is restored.
6. The transaction becomes a completed loan.

### Fine rule

```text
On time or early:
    Fine = 0

Late:
    Fine = overdue days × daily fine rate
```

---

## 14. Initial Demonstration Data

The first application initialization provides demonstration records.

### Books

- Effective Java — Joshua Bloch — 3 copies
- Clean Code — Robert C. Martin — 2 copies
- Designing Data-Intensive Applications — Martin Kleppmann — 2 copies

### Members

- Demo Student
- Demo Faculty

### Administrator

```text
Username: admin
Password: admin123
```

This sample data is included so that the evaluator can immediately test the main workflows.

---

## 15. Expected User Flow

```text
Launch
  ↓
Login
  ↓
Main Menu
  ├── Book Management
  ├── Member Management
  ├── Issue / Return
  ├── Reports
  └── Exit
```

A typical issue workflow is:

```text
Login
  ↓
Issue / Return
  ↓
Issue Book
  ↓
Enter Book ID
  ↓
Enter Member ID
  ↓
Validate availability/member
  ↓
Create issue record
  ↓
Display issue ID and due date
```

---

## 16. Testing and Verification

The project includes a dependency-free smoke test for `FineCalculator`.

The verified scenarios are:

| Scenario | Expected result |
|---|---|
| Return on due date | Fine = 0 |
| Return three days late at 5/day | Fine = 15 |
| Return before due date | Fine = 0 |

The executed test produced:

```text
PASS: on-time
PASS: late
PASS: early
All tests passed.
```

The application itself was also executed to verify representative workflows for book listing, book issue/active loans, member creation, and reporting.

---

## 17. Execution Evidence

The project includes terminal evidence generated from an actual execution using OpenJDK 21.

The screenshots demonstrate:

1. Successful login and book listing.
2. Successful book issue and active-loan display.
3. Member creation and library summary reporting.

The evidence is stored in:

```text
screenshots/
├── 01-book-management.png
├── 02-issue-return.png
├── 03-member-reports.png
└── corresponding .txt terminal transcripts
```

A completed `Project_Report.pdf` is also included in the project root.

---

## 18. Deliverables

The project submission contains:

- Java source code
- Test source code
- Maven project configuration
- README documentation
- Project statement
- System diagrams
- Execution screenshots
- Raw terminal transcripts
- Project report PDF
- Submission checklist

---

## 19. Limitations

The current version is designed for an academic/local demonstration. It is not intended to be deployed as a production multi-user library system.

The main limitations are:

- local single-store persistence;
- command-line-only interface;
- basic demonstration authentication;
- no network access;
- no automated notifications;
- no online payment functionality;
- no external database server;
- limited reporting compared with a production system.

---

## 20. Future Enhancements

Potential improvements include:

1. Web-based user interface.
2. Desktop GUI.
3. PostgreSQL/MySQL database integration.
4. Password hashing and role-based authorization.
5. Search filters by author, category, ISBN, and availability.
6. Automated due-date reminders.
7. Barcode/QR-code scanning.
8. CSV/PDF report export.
9. Reservation/wait-list functionality.
10. Multi-user concurrent access.
11. More comprehensive automated tests.
12. Audit logging for administrative operations.

---

## 21. Conclusion

The Library Management System provides a focused implementation of common library operations in a Java command-line environment. The project demonstrates how a problem can be divided into functional modules and implemented using models, DAOs, services, utilities, validation, local persistence, and reporting.

The resulting application is small enough to understand and demonstrate while still covering multiple end-to-end workflows: authentication, catalogue management, member management, issue/return processing, fine calculation, and reporting.

---


