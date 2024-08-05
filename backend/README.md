## SkillAssess.io

This project is designed for recruiters and candidates to streamline the hiring process. Recruiters can manage assignments, create or modify test questions, and track interview stages, while candidates can view and manage their pending assignments. This system helps improve organization, efficiency, and communication throughout the recruitment process.

**Features**
1. Recruiter
    - **Assignments**: View a list of assignments along with their current progress.
    - **Test Creation**: Access and manage Assessment questions; create new ones or modify existing ones.
    - **User Management**: See a list of users and track their current interview stages.
2. Candidate
    - **Assignment List**: View all pending assignments that are assigned to you


## Set up instructions

**Set the following Environment Variables**
- DB_URL
- DB_USER
- DB_PASSWORD

**Run the following SQL Query**
```commandline
INSERT INTO roles ("id", "name") VALUES (1, 'USER'), (2, 'ADMIN'), (3, 'SUPER ADMIN');
```