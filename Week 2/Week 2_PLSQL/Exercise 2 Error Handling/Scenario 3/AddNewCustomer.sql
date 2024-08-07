CREATE PROCEDURE AddNewCustomer
    @CustomerID INT,
    @CustomerName NVARCHAR(100),
    @CustomerEmail NVARCHAR(100)
AS
BEGIN
    BEGIN TRY
        -- Start a transaction
        BEGIN TRANSACTION;

        -- Check if the customer ID already exists
        IF EXISTS (SELECT 1 FROM Customers WHERE CustomerID = @CustomerID)
        BEGIN
            -- Log the error message
            INSERT INTO ErrorLog (ErrorMessage, ErrorTime)
            VALUES ('Customer ID already exists', GETDATE());

            -- Rollback the transaction
            ROLLBACK TRANSACTION;

            -- Return an error message
            RAISERROR ('Customer ID already exists', 16, 1);
            RETURN;
        END

        -- Insert the new customer
        INSERT INTO Customers (CustomerID, CustomerName, CustomerEmail)
        VALUES (@CustomerID, @CustomerName, @CustomerEmail);

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
