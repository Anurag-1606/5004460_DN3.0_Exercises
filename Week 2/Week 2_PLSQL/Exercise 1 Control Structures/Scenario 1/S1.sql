DECLARE
    v_age NUMBER;
    v_interest_rate NUMBER := 0.01; -- 1% interest rate discount
    v_current_date DATE := SYSDATE;
    CURSOR customer_cursor IS
        SELECT CustomerID, DOB
        FROM Customers;

BEGIN
    FOR customer_rec IN customer_cursor LOOP
        -- Calculate the age of the customer
        SELECT ROUND((v_current_date - customer_rec.DOB) / 365.25)
        INTO v_age
        FROM dual;

        -- Check if the customer is above 60 years old
        IF v_age > 60 THEN
            -- Apply the 1% discount to all loan accounts for the customer
            UPDATE Accounts
            SET Balance = Balance * (1 - v_interest_rate),
                LastModified = v_current_date
            WHERE CustomerID = customer_rec.CustomerID
            AND AccountType = 'Loan';
        END IF;
    END LOOP;
END;
/