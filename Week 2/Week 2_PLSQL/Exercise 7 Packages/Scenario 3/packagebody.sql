CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount (
        p_AccountID IN NUMBER,
        p_CustomerID IN NUMBER,
        p_AccountType IN VARCHAR2,
        p_Balance IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance)
        VALUES (p_AccountID, p_CustomerID, p_AccountType, p_Balance);
    END OpenAccount;

    PROCEDURE CloseAccount (
        p_AccountID IN NUMBER
    ) IS
    BEGIN
        DELETE FROM Accounts
        WHERE AccountID = p_AccountID;
    END CloseAccount;

    FUNCTION GetTotalBalance (
        p_CustomerID IN NUMBER
    ) RETURN NUMBER IS
        v_TotalBalance NUMBER;
    BEGIN
        SELECT SUM(Balance) INTO v_TotalBalance
        FROM Accounts
        WHERE CustomerID = p_CustomerID;

        RETURN NVL(v_TotalBalance, 0); -- Return 0 if no accounts found
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 0; -- Return 0 if no accounts found
    END GetTotalBalance;

END AccountOperations;
/
