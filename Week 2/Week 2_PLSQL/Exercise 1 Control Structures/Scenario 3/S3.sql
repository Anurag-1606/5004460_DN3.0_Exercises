DECLARE
    CURSOR loan_cursor IS
        SELECT a.AccountID, a.CustomerID, a.DueDate, c.Name
        FROM Accounts a
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE a.AccountType = 'Loan'
        AND a.DueDate BETWEEN SYSDATE AND SYSDATE + 30;

BEGIN
    FOR loan_rec IN loan_cursor LOOP
        -- Print a reminder message for each customer
        DBMS_OUTPUT.PUT_LINE('Reminder: Customer ' || loan_rec.Name || 
                             ' (CustomerID: ' || loan_rec.CustomerID || 
                             ') has a loan (AccountID: ' || loan_rec.AccountID || 
                             ') due on ' || TO_CHAR(loan_rec.DueDate, 'DD-MON-YYYY') || '.');
    END LOOP;
END;
/
