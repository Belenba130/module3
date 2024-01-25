CREATE DATABASE IF NOT EXISTS BTVN;
USE BTVN;


-- BẢNG BANNER
CREATE TABLE IF NOT EXISTS Banner (
    id int NOT NULL PRIMARY KEY,
    title NVARCHAR(255),
    image VARBINARY(255),
    summary NVARCHAR(255),
    link VARBINARY(255),
    order_by NVARCHAR(255),
    status BIT
);


-- BẢNG CONTACT
CREATE TABLE IF NOT EXISTS Contacts(
 id int NOT NULL PRIMARY KEY, 
 email nvarchar(255),
 address nvarchar(255),
 message nvarchar(255),
 Status bit);
 
 
 -- BẢNG BLOGS
 CREATE TABLE IF NOT EXISTS Blogs(
 id int NOT NULL PRIMARY KEY, 
 title nvarchar(255),
 image VARBINARY(255),
 content nvarchar(255),	
 Status bit);
 
 
 -- BẢNG ABOUTS
 CREATE TABLE IF NOT EXISTS Abouts(
 id int NOT NULL PRIMARY KEY, 	
 title NVARCHAR(255),
 image VARBINARY(255),
 content nvarchar(255),   
 status bit);
 
 
 -- BẢNG CUSTOMERS
 CREATE TABLE IF NOT EXISTS Customers(
 id int NOT NULL PRIMARY KEY,
 phone char(11),
 email nvarchar(255),
 address nvarchar(255)
 );
 
 
 -- Bảng ORDERS
 CREATE TABLE IF NOT EXISTS Orders(
 id int NOT NULL PRIMARY KEY, 	
 customer_id int not null,
 total int,
 status bit,
 foreign key(customer_id) references Customers(id));
 
 
 -- BẢNG ORDER DETAILS
 CREATE TABLE IF NOT EXISTS ORDER_DETAILS(
 id int NOT NULL PRIMARY KEY, 	
 product_id int not null,
 order_id int not null,
 price float,
 quantity int,
 foreign key (id) references Orders(id),
 foreign key (product_id) references products(id));
 
 
 -- BẢNG CATEGORIES
 CREATE TABLE IF NOT EXISTS Categories(
 id int NOT NULL PRIMARY KEY, 
 name nvarchar(255),
 keyword nvarchar(255),
 description nvarchar(255),	
 Status bit);
 
 
 
 -- BẢNG PRODUCTS
 CREATE TABLE IF NOT EXISTS Products(
 id int NOT NULL PRIMARY KEY, 
 name nvarchar(255),
 category_id int not null,
 image varbinary(255),
 image_list varbinary(255),
 price float,
 sale_price float,
 description nvarchar(255),
 keyword nvarchar(255),
 content nvarchar(255),
 Status bit,
 foreign key (category_id) references Categories(id)
 );
 
 
 -- BẢNG SERVICES
 CREATE TABLE IF NOT EXISTS Services(
 id int NOT NULL PRIMARY KEY, 
 name nvarchar(255),
 summary nvarchar(255),
 content nvarchar(255),
 image varbinary(255),
 order_by nvarchar(255),
 Status bit);
 
 
 -- BẢNG USERS
 CREATE TABLE IF NOT EXISTS Users(
 id int NOT NULL PRIMARY KEY
 );
 
 
 -- BẢNG BOOKS
 CREATE TABLE IF NOT EXISTS Books(
 id int NOT NULL PRIMARY KEY, 
 user_id int not null,
 service_id int not null,
 name nvarchar(255),
 phone char(11),
 date date,
 Status bit,
 foreign key (user_id) references Users(id),
 foreign key (service_id) references Services(id)
 );
 
 
-- 	BẢNG COMMENTS
CREATE TABLE IF NOT EXISTS Comments(
 id int NOT NULL PRIMARY KEY, 
 user_id int not null,
 product_id int not null,
 message nvarchar(255),	
 Status bit,
 foreign key (user_id) references users(id),
 foreign key (product_id) references products(id)
 );
 show tables;

