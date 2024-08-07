DECLARE
    -- Define a constant for the annual maintenance fee
    c_annual_fee NUMBER := 50; -- Example fee amount

    -- Define a record type for the cursor
    TYPE account_record IS RECORD (
        account_id      accounts.account_id%TYPE,
        account_balance  accounts.account_balance%TYPE
    );

    -- Define a cursor to select all accounts
    CURSOR ApplyAnnualFee IS
        SELECT account_id, account_balance
        FROM accounts
        FOR UPDATE;  -- Use FOR UPDATE to lock the rows for update

    -- Variable to hold the fetched record
    v_account account_record;

BEGIN
    -- Open the cursor
    OPEN ApplyAnnualFee;

    -- Loop through the cursor to process each account
    LOOP
        FETCH ApplyAnnualFee INTO v_account;
        EXIT WHEN ApplyAnnualFee%NOTFOUND;

        -- Deduct the annual maintenance fee from the account balance
        v_account.account_balance := v_account.account_balance - c_annual_fee;

        -- Update the account balance in the database
        UPDATE accounts
        SET account_balance = v_account.account_balance
        WHERE CURRENT OF ApplyAnnualFee;  -- Use CURRENT OF to update the current row

    END LOOP;

    -- Close the cursor
    CLOSE ApplyAnnualFee;

    -- Commit the changes
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        -- Handle exceptions
        IF ApplyAnnualFee%ISOPEN THEN
            CLOSE ApplyAnnualFee;
        END IF;
        RAISE;

END;
/
