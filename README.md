# project1-SilvanGrosskreutz - Expense Reimbursement System (ERS)

## Project Description
My project is an Expense Reimbursement System (ERS). You can register as an Employee or as a Finance Manager. If you log in as a Employee you can create a Reimbursement
for expenses you did for work. You can select between the types Lodging, Food, Travel and Other and choose the amount. A Finance Manager can log in, see all the Reimbursements and can either accept or deny them.

## Technology Used
- Postgres Version 42.3.4 is used for the backend
- Java 8 is used for the Middle Tier
- Java Servlet Api Version 4.0.1 is used to connect to the Frontend
- Jackson Databind Version 2.13.2.2 is used for Json conversion
- HTML5/CSS(bootstrap Version 5)/JavaScript is used for the Frontend

## Features
List of features ready and TODOs for future development
- Can register and login as Employee or Finance Manager
- Employee can create Reimbursement, Finance Manager can resolve it

To-do list:
- Structure the Backend table in 3rd Normalform
- Improve the html pages to show more responses
- Logout for Users

## Getting started
1. Go on the project, click on the green "code" button in the right top corner and copy the https link
2. Open the program "git" at the location you want the project to be
3. In the git console type "git clone" and put the link behind it
4. In the ConnectionFactory class you have to put the credentials for your database (AWS)
![grafik](https://user-images.githubusercontent.com/103101627/170120077-2025f66b-3593-4381-a5d1-87f0db0d4b50.png)
5. Create the User table in your database with the command:
```
CREATE TABLE users(
	user_id SERIAL PRIMARY KEY,
	user_name VARCHAR(50) UNIQUE,
	user_password VARCHAR(50) NOT NULL,
	user_role VARCHAR(50) NOT NULL,
	user_first_name VARCHAR(50),
	user_last_name VARCHAR(50),
	user_email VARCHAR(200),
	user_phonenumber VARCHAR(20)
);
```
6. Create the Reimbursement table with the command:
```
CREATE TABLE reimbursement(
	reim_id SERIAL PRIMARY KEY,
	reim_status VARCHAR(50) NOT NULL,
	reim_author VARCHAR(100) NOT NULL,
	reim_resolver VARCHAR(100),
	reim_type VARCHAR(200) NOT NULL,
	reim_amount NUMERIC(10,2) NOT NULL
);
```


