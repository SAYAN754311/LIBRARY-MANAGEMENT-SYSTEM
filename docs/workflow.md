# Workflow Diagram

```mermaid
flowchart TD
    A[Start] --> B[Administrator Login]
    B -->|Valid| C[Main Menu]
    B -->|Invalid| B
    C --> D[Book Management]
    C --> E[Member Management]
    C --> F[Issue / Return]
    C --> G[Reports]
    D --> C
    E --> C
    F --> C
    G --> C
    C --> H[Exit]
```