# GitHub Upload Guide

## Option A — GitHub website

1. Create a new **Public** repository named `LibraryManagementSystem`.
2. Do not add another README, `.gitignore`, or license when creating it because this project already contains them.
3. Extract this project ZIP.
4. Upload the project contents so that `README.md` is directly at the repository root.
5. Commit the files.
6. Open the repository in an incognito/private browser window and confirm that the README and source folders are visible.

## Option B — Git command line

From this project folder:

```bash
git init
git add .
git commit -m "Initial Library Management System project"
git branch -M main
git remote add origin https://github.com/<YOUR_USERNAME>/LibraryManagementSystem.git
git push -u origin main
```

Replace `<YOUR_USERNAME>` and use the exact repository URL from GitHub.

## Final URL

The submission URL should look like:

```text
https://github.com/<YOUR_USERNAME>/LibraryManagementSystem
```
