# RestAPI
My first Spring Boot RESTful API. 

This is a Project in Which I've Completed this below Task:- 

Database tables
---------------------------------------------------------------------
- manually create below tables MySQL database

department
- id                  (primary key - auto generated)
- name
- description
- created_time
- updated_time


employee
- id                  (primary key - auto generated)
- name
- email
- birth_date
- created_time
- updated_time
- department_id       (foreign key - department.id)


project
- id                  (primary key - auto generated)
- name
- description
- is_active
- created_time
- updated_time


project_team_member

- project_id          (foreign key - project.id)
- employee_id         (foreign key - employee.id)
                      *use both column project_id + employee_id as primary key




API exercise (Phase 1):
---------------------------------------------------------------------

- create above mentioned table in mysql database manually
- study about ResponseEntity class and use it response for each api.
- create below apis

Department api:

GET /api/departments - list of departments
GET /api/departments/{departmentId} - get single department
POST /api/departments - create department
PUT /api/departments/{departmentId} - update existing department
DELETE /api/departments/{departmentId} - delete department


Employee api:

GET /api/employees - list of employees
GET /api/employees/{employeeId} - get employee detail
POST /api/employees - add new employee
PUT /api/employees/{employeeId} - update existing employee
DELETE /api/employees/{employeeId} - delete employee


Project api:

GET /api/projects - list of projects
GET /api/projects/{projectId} - get project detail
POST /api/projects - add new project
PUT /api/projects/{projectId} - update existing project
DELETE /api/projects/{projectId} - delete project


Project team member api:

GET /api/project_teams - list of project team members
POST /api/project_teams - add new entry in project team member
DELETE /api/projects/{projectId} - delete project team members ==>I think: it's a wrong url path :- Correct --> DELETE /api/project_teams/{projectId} - delete project team members





API exercise (Phase 2):
---------------------------------------------------------------------

- explore JPA Annotations for relationships - OneToOne, OneToMany, ManyToOne, ManyToMany.
- update existing api (do not create new api):

Employee api:

GET /api/employees/{employeeId} - in api response get employee details + department detail + list of projects details (based on project_team_member table)

Project api:

GET /api/projects/{projectId} - get project detail + list of employees who are involve in that project (based on project_team_member table)
POST /api/projects - in request provide project detail + employee ids who are involved in this project. which will add entry in project table + project_team_member table.
PUT /api/projects/{projectId} - update existing project detail + employee ids in project_team_member table as per request body.
DELETE /api/projects/{projectId} - delete project + remove entries from project_team_member table

Project team member api:

- remove all three api from code that we created in phase 1



