DECLARE
    -- Define a record type for the cursor
    TYPE loan_record IS RECORD (
        loan_id        loans.loan_id%TYPE,
        interest_rate   loans.interest_rate%TYPE
    );

    -- Define a cursor to select all loans
    CURSOR UpdateLoanInterestRates IS
        SELECT loan_id, interest_rate
        FROM loans
        FOR UPDATE;  -- Use FOR UPDATE to lock the rows for update

    -- Variable to hold the fetched record
    v_loan loan_record;

    -- New interest rate based on the policy
    v_new_interest_rate NUMBER;

BEGIN
    -- Open the cursor
    OPEN UpdateLoanInterestRates;

    -- Loop through the cursor to process each loan
    LOOP
        FETCH UpdateLoanInterestRates INTO v_loan;
        EXIT WHEN UpdateLoanInterestRates%NOTFOUND;

        -- Determine the new interest rate based on the policy
        -- (For demonstration, let's increase the rate by 1% for all loans)
        v_new_interest_rate := v_loan.interest_rate + 1;

        -- Update the interest rate in the database
        UPDATE loans
        SET interest_rate = v_new_interest_rate
        WHERE CURRENT OF UpdateLoanInterestRates;  -- Use CURRENT OF to update the current row

    END LOOP;

    -- Close the cursor
    CLOSE UpdateLoanInterestRates;

    -- Commit the changes
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        -- Handle exceptions
        IF UpdateLoanInterestRates%ISOPEN THEN
            CLOSE UpdateLoanInterestRates;
        END IF;
        RAISE;

END;
/
