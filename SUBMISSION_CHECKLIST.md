# GitHub Submission Checklist

## Before upload
- [ ] Replace `Student Name`, `Register Number`, `Course / Class`, and `Institution` placeholders where required.
- [ ] Review `README.md` and `statement.md`.
- [ ] Verify `Project_Report.pdf` contains the correct student information.
- [ ] Keep generated runtime data (`data/library.db`) out of Git.

## Build verification
- [ ] `javac -d out $(find src/main/java -name '*.java')` succeeds.
- [ ] `java -cp out com.library.Main` starts successfully.
- [ ] `java -cp out com.library.FineCalculatorTest` prints `All tests passed.`
- [ ] If Maven is available, `mvn clean compile` succeeds.

## GitHub
- [ ] Create a new repository named `LibraryManagementSystem` (or another clear name).
- [ ] Set repository visibility to **Public** if required by the submission portal.
- [ ] Upload/push the complete project, including `README.md` at repository root.
- [ ] Do not commit passwords, IDE files, build output, or local runtime data.
- [ ] Open the public repository in a private/incognito browser window and verify the README renders.
- [ ] Submit only the repository root URL if the portal requests the root URL.
