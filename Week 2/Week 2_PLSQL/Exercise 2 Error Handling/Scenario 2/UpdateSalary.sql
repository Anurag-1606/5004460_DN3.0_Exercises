CREATE PROCEDURE UpdateSalary
    @EmployeeID INT,
    @PercentageIncrease DECIMAL(5, 2)
AS
BEGIN
    BEGIN TRY
        -- Start a transaction
        BEGIN TRANSACTION;

        -- Check if the employee ID exists
        IF NOT EXISTS (SELECT 1 FROM Employees WHERE EmployeeID = @EmployeeID)
        BEGIN
            -- Log the error message
            INSERT INTO ErrorLog (ErrorMessage, ErrorTime)
            VALUES ('Employee ID does not exist', GETDATE());

            -- Rollback the transaction
            ROLLBACK TRANSACTION;

            -- Return an error message
            RAISERROR ('Employee ID does not exist', 16, 1);
            RETURN;
        END

        -- Update the salary of the employee
        UPDATE Employees
        SET Salary = Salary * (1 + @PercentageIncrease / 100)
        WHERE EmployeeID = @EmployeeID;

        -- Commit the transaction
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        -- Rollback the transaction in case of any error
        ROLLBACK TRANSACTION;

        -- Log the error message
        INSERT INTO ErrorLog (ErrorMessage, ErrorTime)
        VALUES (ERROR_MESSAGE(), GETDATE());

        -- Re-throw the error
        THROW;
    END CATCH
END;
GO
