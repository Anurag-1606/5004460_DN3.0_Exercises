CREATE PROCEDURE SafeTransferFunds
    @FromAccountID INT,
    @ToAccountID INT,
    @Amount DECIMAL(18, 2)
AS
BEGIN
    BEGIN TRY
        -- Start a transaction
        BEGIN TRANSACTION;

        -- Check if the from account has sufficient funds
        IF (SELECT Balance FROM Accounts WHERE AccountID = @FromAccountID) < @Amount
        BEGIN
            -- Log the error message
            INSERT INTO ErrorLog (ErrorMessage, ErrorTime)
            VALUES ('Insufficient funds', GETDATE());

            -- Rollback the transaction
            ROLLBACK TRANSACTION;

            -- Return an error message
            RAISERROR ('Insufficient funds in the source account', 16, 1);
            RETURN;
        END

        -- Deduct the amount from the from account
        UPDATE Accounts
        SET Balance = Balance - @Amount
        WHERE AccountID = @FromAccountID;

        -- Add the amount to the to account
        UPDATE Accounts
        SET Balance = Balance + @Amount
        WHERE AccountID = @ToAccountID;

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
