CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee (
        p_EmployeeID IN NUMBER,
        p_Name IN VARCHAR2,
        p_JobTitle IN VARCHAR2,
        p_Salary IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, JobTitle, Salary)
        VALUES (p_EmployeeID, p_Name, p_JobTitle, p_Salary);
    END HireEmployee;

    PROCEDURE UpdateEmployee (
        p_EmployeeID IN NUMBER,
        p_Name IN VARCHAR2,
        p_JobTitle IN VARCHAR2,
        p_Salary IN NUMBER
    ) IS
    BEGIN
        UPDATE Employees
        SET Name = p_Name,
            JobTitle = p_JobTitle,
            Salary = p_Salary
        WHERE EmployeeID = p_EmployeeID;
    END UpdateEmployee;

    FUNCTION CalculateAnnualSalary (
        p_EmployeeID IN NUMBER
    ) RETURN NUMBER IS
        v_AnnualSalary NUMBER;
    BEGIN
        SELECT Salary * 12 INTO v_AnnualSalary
        FROM Employees
        WHERE EmployeeID = p_EmployeeID;

        RETURN v_AnnualSalary;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL; -- or handle accordingly
    END CalculateAnnualSalary;

END EmployeeManagement;
/
