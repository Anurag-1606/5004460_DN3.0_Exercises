CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer (
        p_CustomerID IN NUMBER,
        p_Name IN VARCHAR2,
        p_Email IN VARCHAR2,
        p_Balance IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, Email, Balance)
        VALUES (p_CustomerID, p_Name, p_Email, p_Balance);
    END AddCustomer;

    PROCEDURE UpdateCustomer (
        p_CustomerID IN NUMBER,
        p_Name IN VARCHAR2,
        p_Email IN VARCHAR2
    ) IS
    BEGIN
        UPDATE Customers
        SET Name = p_Name,
            Email = p_Email
        WHERE CustomerID = p_CustomerID;
    END UpdateCustomer;

    FUNCTION GetCustomerBalance (
        p_CustomerID IN NUMBER
    ) RETURN NUMBER IS
        v_Balance NUMBER;
    BEGIN
        SELECT Balance INTO v_Balance
        FROM Customers
        WHERE CustomerID = p_CustomerID;

        RETURN v_Balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL; -- or handle accordingly
    END GetCustomerBalance;

END CustomerManagement;
/
