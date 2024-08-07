CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE,
    IsVIP CHAR(1) DEFAULT 'N' -- Assuming 'N' for not VIP and 'Y' for VIP
);
