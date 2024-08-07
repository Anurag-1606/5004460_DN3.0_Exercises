CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    -- Retrieve the current balance of the account
    SELECT Balance INTO v_balance
    FROM Customers
    WHERE CustomerID = :NEW.AccountID;

    -- Check the transaction type and enforce rules
    IF :NEW.TransactionType = 'WITHDRAWAL' THEN
        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20001, 'Withdrawal exceeds current balance.');
        END IF;
    ELSIF :NEW.TransactionType = 'DEPOSIT' THEN
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.');
        END IF;
    ELSE
        RAISE_APPLICATION_ERROR(-20003, 'Invalid transaction type. Only DEPOSIT or WITHDRAWAL allowed.');
    END IF;
END;
/
