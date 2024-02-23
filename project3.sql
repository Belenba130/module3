create database if not exists MySQL;
use MySQL;
CREATE TABLE PRODUCT (
    Product_Id CHAR(5) PRIMARY KEY,
    Product_Name VARCHAR(150) NOT NULL UNIQUE,
    Manufacturer VARCHAR(200) NOT NULL,
	Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Batch SMALLINT NOT NULL,
    Quantity INT NOT NULL DEFAULT 0,
    Product_Status BIT DEFAULT 1
);

CREATE TABLE EMPLOYEE (
    Emp_Id CHAR(5) PRIMARY KEY,
    Emp_Name VARCHAR(100) NOT NULL UNIQUE,
    Birth_Of_Date DATE,
    Email VARCHAR(100) NOT NULL,
    Phone VARCHAR(100) NOT NULL,
    Address TEXT NOT NULL,
    Emp_Status SMALLINT NOT NULL
);

CREATE TABLE ACCOUNT (
    Acc_id INT PRIMARY KEY AUTO_INCREMENT,
    User_name VARCHAR(30) NOT NULL UNIQUE,
    Password VARCHAR(30) NOT NULL,
    Permission BIT DEFAULT 1,
    Emp_id CHAR(5) NOT NULL UNIQUE,
    Acc_status BIT DEFAULT 1
);

CREATE TABLE BILL (
    Bill_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    Bill_Code VARCHAR(10) NOT NULL,
    Bill_Type TINYINT(1) NOT NULL,
    Emp_id_created CHAR(5) NOT NULL,
    Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Emp_id_auth CHAR(5) NOT NULL,
    Auth_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    Bill_Status SMALLINT NOT NULL DEFAULT 0,
    FOREIGN KEY (Emp_id_created) REFERENCES EMPLOYEE(Emp_Id),
    FOREIGN KEY (Emp_id_auth) REFERENCES EMPLOYEE(Emp_Id)
);

CREATE TABLE BILL_DETAIL (
    Bill_Detail_Id BIGINT PRIMARY KEY AUTO_INCREMENT,
    Bill_Id BIGINT NOT NULL,
    Product_Id CHAR(5) NOT NULL,
    Quantity INT NOT NULL CHECK (Quantity > 0),
    Price FLOAT NOT NULL CHECK (Price > 0),
    FOREIGN KEY (Bill_Id) REFERENCES BILL(Bill_id),
    FOREIGN KEY (Product_Id) REFERENCES PRODUCT(Product_Id)
);

INSERT INTO PRODUCT (Product_Id, Product_Name, Manufacturer, Batch, Quantity, Product_Status) 
VALUES ('P001', 'Product A', 'Manufacturer X', 1, 100, 1),
       ('P002', 'Product B', 'Manufacturer Y', 2, 150, 1),
       ('P003', 'Product C', 'Manufacturer Z', 1, 200, 1);
INSERT INTO EMPLOYEE (Emp_Id, Emp_Name, Birth_Of_Date, Email, Phone, Address, Emp_Status)
VALUES ('E001', 'John Doe', '1990-01-01', 'john@example.com', '123456789', '123 Main St', 0),
       ('E002', 'Jane Smith', '1992-05-15', 'jane@example.com', '987654321', '456 Elm St', 0),
       ('E003', 'Michael Johnson', '1988-09-30', 'michael@example.com', '111222333', '789 Oak St', 0);
INSERT INTO ACCOUNT (Acc_id, User_name, Password, Permission, Emp_id, Acc_status)
VALUES (1, 'admin', 'admin123', 0, 'E001', 1),
       (2, 'user1', 'user123', 1, 'E002', 1),
       (3, 'user2', 'user456', 1, 'E003', 1);
