create database if not exists datas;
use datas;
CREATE TABLE Categories (
    CategoryID INT AUTO_INCREMENT PRIMARY KEY,
    CategoryName VARCHAR(100) NOT NULL,
    Description TEXT,
    ParentCategoryID INT,
    FOREIGN KEY (ParentCategoryID) REFERENCES Categories(CategoryID)
);
CREATE TABLE Products (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    ProductName VARCHAR(250) NOT NULL,
    CategoryID INT,
    Price DECIMAL(10, 2),
    Description TEXT,
    ImageURL VARCHAR(1000),
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);
CREATE TABLE Customers (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    FullName VARCHAR(100) NOT NULL,
    Email VARCHAR(100),
    Phone VARCHAR(20),
    Address VARCHAR(255)
);
CREATE TABLE Orders (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT,
    OrderDate DATETIME,
    TotalAmount DECIMAL(10, 2),
    Status ENUM('Pending', 'Shipped', 'Delivered', 'Cancelled'),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);
CREATE TABLE OrderDetails (
    OrderDetailID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT,
    ProductID INT,
    Quantity INT,
    Price DECIMAL(10, 2),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
CREATE TABLE Reviews (
    ReviewID INT AUTO_INCREMENT PRIMARY KEY,
    ProductID INT,
    CustomerID INT,
    Rating INT,
    Comment TEXT,
    ReviewDate DATETIME,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);


INSERT INTO Categories (CategoryName, Description, ParentCategoryID)
VALUES 
('Men', 'Men''s Clothing', NULL),
('Women', 'Women''s Clothing', NULL),
('Shirts', 'Men''s Shirts', 1),
('Pants', 'Men''s Pants', 1),
('Dresses', 'Women''s Dresses', 2),
('Skirts', 'Women''s Skirts', 2);

INSERT INTO Products (ProductName, CategoryID, Price, Description, ImageURL)
VALUES 
('Men''s Casual Shirt', 3, 29.99, 'Casual shirt for men', 'image_url'),
('Men''s Dress Pants', 4, 49.99, 'Formal dress pants for men', 'image_url'),
('Women''s Summer Dress', 5, 39.99, 'Lightweight summer dress for women', 'image_url'),
('Women''s A-Line Skirt', 6, 34.99, 'A-line skirt for women', 'image_url');

INSERT INTO Customers (FullName, Email, Phone, Address)
VALUES 
('John Doe', 'john@example.com', '123-456-7890', '123 Main St'),
('Jane Smith', 'jane@example.com', '456-789-1230', '456 Elm St'),
('Alice Johnson', 'alice@example.com', '555-123-4567', '789 Oak St, Village'),
('Bob Williams', 'bob@example.com', '555-987-6543', '456 Maple St, Town'),
('Charlie Brown', 'charlie@example.com', '555-234-5678', '321 Pine St, City'),
('David Lee', 'david@example.com', '555-876-5432', '987 Birch St, Village'),
('Eve Taylor', 'eve@example.com', '555-345-6789', '654 Cedar St, Town');

INSERT INTO Orders (CustomerID, OrderDate, TotalAmount, Status)
VALUES 
(1, '2024-01-26 10:00:00', 79.98, 'Delivered'),
(2, '2024-01-27 11:00:00', 74.98, 'Pending'),
(3, '2024-01-28 12:00:00', 150.97, 'Shipped'),
(4, '2024-01-29 13:00:00', 95.98, 'Pending'),
(5, '2024-01-30 14:00:00', 230.96, 'Delivered'),
(1, '2024-01-31 15:00:00', 180.98, 'Pending'),
(2, '2024-02-01 16:00:00', 125.99, 'Shipped');

INSERT INTO OrderDetails (OrderID, ProductID, Quantity, Price)
VALUES 
(1, 1, 2, 29.99),
(1, 4, 1, 49.99),
(2, 3, 1, 39.99),
(2, 4, 1, 34.99);

-- Order 3 Details
INSERT INTO OrderDetails (OrderID, ProductID, Quantity, Price)
VALUES 
(3, 1, 3, 29.99),
(3, 4, 2, 49.99);

-- Order 4 Details
INSERT INTO OrderDetails (OrderID, ProductID, Quantity, Price)
VALUES 
(4, 2, 1, 39.99),
(4, 3, 1, 39.99),
(4, 4, 2, 34.99);

-- Order 5 Details
INSERT INTO OrderDetails (OrderID, ProductID, Quantity, Price)
VALUES 
(5, 1, 2, 29.99),
(5, 2, 1, 49.99),
(5, 3, 3, 39.99),
(5, 4, 2, 34.99);

-- Order 6 Details
INSERT INTO OrderDetails (OrderID, ProductID, Quantity, Price)
VALUES 
(6, 1, 1, 29.99),
(6, 4, 2, 49.99),
(6, 3, 1, 39.99),
(6, 2, 3, 49.99);

-- Order 7 Details
INSERT INTO OrderDetails (OrderID, ProductID, Quantity, Price)
VALUES 
(7, 2, 2, 49.99),
(7, 3, 1, 39.99),
(7, 4, 1, 34.99),
(7, 1, 1, 29.99);


-- Review 1
INSERT INTO Reviews (ProductID, CustomerID, Rating, Comment, ReviewDate)
VALUES (1, 1, 4, 'Great shirt, fits perfectly!', '2024-01-15 10:00:00');

-- Review 2
INSERT INTO Reviews (ProductID, CustomerID, Rating, Comment, ReviewDate)
VALUES (1, 2, 5, 'Love the quality and design.', '2024-01-16 11:00:00');

-- Review 3
INSERT INTO Reviews (ProductID, CustomerID, Rating, Comment, ReviewDate)
VALUES (2, 3, 3, 'The pants are a bit tight.', '2024-01-17 12:00:00');

-- Review 4
INSERT INTO Reviews (ProductID, CustomerID, Rating, Comment, ReviewDate)
VALUES (3, 4, 5, 'Beautiful dress, perfect for summer!', '2024-01-18 13:00:00');

-- Review 5
INSERT INTO Reviews (ProductID, CustomerID, Rating, Comment, ReviewDate)
VALUES (3, 5, 4, 'Comfortable and stylish.', '2024-01-19 14:00:00');

-- Review 6
INSERT INTO Reviews (ProductID, CustomerID, Rating, Comment, ReviewDate)
VALUES (4, 1, 4, 'Nice skirt, good quality material.', '2024-01-20 15:00:00');

-- Review 7
INSERT INTO Reviews (ProductID, CustomerID, Rating, Comment, ReviewDate)
VALUES (4, 2, 5, 'Fits perfectly, very happy with my purchase!', '2024-01-21 16:00:00');

-- 1. Báo cáo tổng doanh thu từng đơn hàng
SELECT 
    O.OrderID AS 'Mã đơn hàng',
    SUM(OD.Price * OD.Quantity) AS 'Tổng doanh thu'
FROM 
    Orders O
JOIN 
    OrderDetails OD ON O.OrderID = OD.OrderID
GROUP BY 
    O.OrderID;

-- 2. Báo cáo số lượng sản phẩm đã bán theo danh mục
SELECT 
    C.CategoryName AS 'Danh mục',
    SUM(OD.Quantity) AS 'Số lượng sản phẩm đã bán'
FROM 
    Categories C
JOIN 
    Products P ON C.CategoryID = P.CategoryID
JOIN 
    OrderDetails OD ON P.ProductID = OD.ProductID
GROUP BY 
    C.CategoryID, C.CategoryName;

-- 3. Báo cáo danh sách khách hàng và số lượng đơn hàng mỗi khách hàng đã đặt
SELECT
U.FullName AS 'Tên KH',
COUNT(O.OrderID) AS 'SỐ HÀNG ĐÃ MUA'
FROM
Customers U
JOIN
Orders O on U.CustomerID= O.CustomerID
GROUP BY
U.customerID,U.FullName;

-- 4. Báo cáo tỷ lệ đơn hàng đã giao thành công
SELECT 
    SUM(CASE WHEN O.Status = 'Delivered' THEN 1 ELSE 0 END) AS 'Đơn hàng thành công',
    COUNT(*) AS 'Tổng số đơn hàng',
    CONCAT(
        FORMAT(
            (SUM(CASE WHEN O.Status = 'Delivered' THEN 1 ELSE 0 END) / COUNT(*)) * 100,2),
        '%'
    ) AS 'Tỷ lệ thành công'
FROM 
    Orders O;

-- 5. Báo cáo đánh giá sản phẩm và điểm đánh giá trung bình cho mỗi sản phẩm
SELECT
    P.ProductName AS 'Tên Sản phẩm',
    R.Comment AS 'Đánh giá',
    AVG(R.Rating) AS 'Điểm đánh giá trung bình'
FROM
    Reviews R
JOIN
    Products P ON P.ProductID = R.ProductID
GROUP BY
    R.ProductID, R.Comment;

-- 6. Liệt kê các sản phẩm được đặt hàng nhiều nhất
select
P.ProductName as 'Tên SP',
Max(Quantity) as 'Số Lượng'
from 
products P
join 
OrderDetails OD on P.productID = OD.productID
group by
P.ProductName;

-- 7. Tìm kiếm sản phẩm dựa trên mức đánh giá trung bình
select 
P.ProductName as 'Tên SP',
avg(Rating) as 'Điểm TB'
From 
products P
Join reviews R on R.ProductID = P.ProductID
group by
P.ProductName
having avg(R.Rating) >=4;

-- 8. Tìm khách hàng có đơn hàng có giá trị cao nhất
SELECT
C.FullName AS 'Tên KH',
MAX(O.TotalAmount) AS 'Giá Trị Mua Cao Nhất'
FROM
Customers C
JOIN
Orders O ON O.CustomerID = C.CustomerID
GROUP BY
C.FullName
HAVING
MAX(O.TotalAmount) = (SELECT MAX(TotalAmount) FROM Orders);

-- 9. Tổng doanh thu từng tháng trong năm
SELECT
    MONTH(OrderDate) AS 'Tháng',
    SUM(TotalAmount) AS 'Tổng Doanh Thu'
FROM
    Orders
GROUP BY
    MONTH(OrderDate);







