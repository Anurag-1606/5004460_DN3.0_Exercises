CREATE OR REPLACE PACKAGE EmployeeManagement AS
    -- Procedure to hire a new employee
    PROCEDURE HireEmployee (
        p_EmployeeID IN NUMBER,
        p_Name IN VARCHAR2,
        p_JobTitle IN VARCHAR2,
        p_Salary IN NUMBER
    );

    -- Procedure to update employee details
    PROCEDURE UpdateEmployee (
        p_EmployeeID IN NUMBER,
        p_Name IN VARCHAR2,
        p_JobTitle IN VARCHAR2,
        p_Salary IN NUMBER
    );

    -- Function to calculate annual salary
    FUNCTION CalculateAnnualSalary (
        p_EmployeeID IN NUMBER
    ) RETURN NUMBER;
END EmployeeManagement;
/
