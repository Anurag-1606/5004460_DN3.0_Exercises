CREATE OR REPLACE PACKAGE CustomerManagement AS
    -- Procedure to add a new customer
    PROCEDURE AddCustomer (
        p_CustomerID IN NUMBER,
        p_Name IN VARCHAR2,
        p_Email IN VARCHAR2,
        p_Balance IN NUMBER
    );

    -- Procedure to update customer details
    PROCEDURE UpdateCustomer (
        p_CustomerID IN NUMBER,
        p_Name IN VARCHAR2,
        p_Email IN VARCHAR2
    );

    -- Function to get customer balance
    FUNCTION GetCustomerBalance (
        p_CustomerID IN NUMBER
    ) RETURN NUMBER;
END CustomerManagement;
/
