# Electricity Billing System

## Overview

The Electricity Billing System is a Java Swing application designed to manage and generate electricity bills for customers. This system uses a MySQL database to store and retrieve customer data and billing information. The application has two main panels: the Admin Panel and the Customer Panel.

## Features

- **Admin Panel:**
    - Add, update, and delete customer records.
    - Generate and manage bills.
    - View all customer billing information.
    - Access detailed reports and analytics.

- **Customer Panel:**
    - View personal billing information.
    - Download and print bills.
    - Update personal information.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Runtime Environment (JRE) installed on your computer.
- MySQL database server installed and running.
- A MySQL client to set up the database (e.g., MySQL Workbench).

## Installation

Follow these steps to set up and run the Electricity Billing System:

### 1. Download the JAR file

Download the JAR file from the releases section of this repository:

[Download Electricity Billing System JAR](https://github.com/your-username/electricity-billing-system/releases)

### 2. Set up the MySQL database

- Open MySQL Workbench or your preferred MySQL client.
- Create a new database called `electricity_billing`.
- Run the following SQL script to create the necessary tables:

```sql
CREATE DATABASE user_signup;
use user_signup;

CREATE TABLE signup (
    meter_no VARCHAR(20),
    username VARCHAR(30),
    name_u VARCHAR(20),
    password_u VARCHAR(20),
    user_u VARCHAR(20)
);

SET SQL_SAFE_UPDATES = 0;

select *  from signup;

create table customer(
name varchar(25),
meter_no varchar(20),
address varchar(50),
city varchar(30),
state varchar(30),
email varchar(30),
phone varchar(10)
);

select * from customer;

create table meter_info(
meter_no varchar(20),
meter_location varchar(20),
meter_type varchar(20),
phase_code varchar(20),
bill_type varchar(20),
days varchar(5)
);

select * from meter_info;


create table tax(
cost_per_unit varchar(3),
meter_rent varchar(3),
service_charge varchar(3),
service_tax varchar(3),
fixed_tax varchar(3)
);

insert into tax values('9','47','22','57','18');

select * from tax;

create table bill(
meter_no varchar(20),
month varchar(25),
units varchar(30),
totalbill varchar(20),
status varchar(20)
);

select * from bill;
```
### 4. Run the application

- Open a terminal or command prompt.
- Navigate to the directory where the JAR file is located.
- Run the application using the following command:

```jar
java -jar electricity-billing-system.jar
```
## Usage
### Admin Panel
1. Log in using admin credentials.
2. Manage customer records (add, update, delete).
3. Generate bills for customers.
4. View all customers' billing information and reports.

### Customer Panel
1. Log in using customer credentials.
2. View personal billing information.
3. Download and print bills.
4. Update personal information.

## Contributing
Contributions are welcome! 

## Contact
If you have any questions or suggestions, feel free to contact me at krutikgami354@gmail.com.
