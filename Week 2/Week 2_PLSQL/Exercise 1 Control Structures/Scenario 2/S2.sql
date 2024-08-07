DECLARE
    CURSOR customer_cursor IS
        SELECT CustomerID, Balance
        FROM Customers;

BEGIN
    FOR customer_rec IN customer_cursor LOOP
        -- Check if the customer's balance is over $10,000
        IF customer_rec.Balance > 10000 THEN
            -- Set the IsVIP flag to 'Y' (True)
            UPDATE Customers
            SET IsVIP = 'Y',
                LastModified = SYSDATE
            WHERE CustomerID = customer_rec.CustomerID;
        ELSE
            -- Optionally, you can reset the IsVIP flag to 'N' if balance is not over $10,000
            UPDATE Customers
            SET IsVIP = 'N',
                LastModified = SYSDATE
            WHERE CustomerID = customer_rec.CustomerID;
        END IF;
    END LOOP;
END;
/
