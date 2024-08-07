DECLARE
    -- Define a record type for the cursor
    TYPE transaction_record IS RECORD (
        customer_id     transactions.customer_id%TYPE,
        transaction_date transactions.transaction_date%TYPE,
        amount          transactions.amount%TYPE
    );

    -- Define a cursor to fetch transactions for the current month
    CURSOR GenerateMonthlyStatements IS
        SELECT customer_id, transaction_date, amount
        FROM transactions
        WHERE EXTRACT(MONTH FROM transaction_date) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM transaction_date) = EXTRACT(YEAR FROM SYSDATE)
        ORDER BY customer_id;

    -- Variable to hold the fetched record
    v_transaction transaction_record;

    -- Variable to track the total amount for each customer
    v_total_amount NUMBER := 0;
    v_current_customer_id transactions.customer_id%TYPE;

BEGIN
    -- Open the cursor
    OPEN GenerateMonthlyStatements;

    -- Loop through the cursor to process each transaction
    LOOP
        FETCH GenerateMonthlyStatements INTO v_transaction;
        EXIT WHEN GenerateMonthlyStatements%NOTFOUND;

        -- If the customer ID changes, print the statement for the previous customer
        IF v_current_customer_id IS NULL OR v_current_customer_id != v_transaction.customer_id THEN
            -- If this is not the first customer, print the total for the previous customer
            IF v_current_customer_id IS NOT NULL THEN
                DBMS_OUTPUT.PUT_LINE('Monthly Statement for Customer ID: ' || v_current_customer_id);
                DBMS_OUTPUT.PUT_LINE('Total Amount: ' || v_total_amount);
                DBMS_OUTPUT.PUT_LINE('------------------------------------------');
            END IF;

            -- Reset the total amount for the new customer
            v_current_customer_id := v_transaction.customer_id;
            v_total_amount := 0;
        END IF;

        -- Accumulate the total amount for the current customer
        v_total_amount := v_total_amount + v_transaction.amount;
    END LOOP;

    -- Print the statement for the last customer
    IF v_current_customer_id IS NOT NULL THEN
        DBMS_OUTPUT.PUT_LINE('Monthly Statement for Customer ID: ' || v_current_customer_id);
        DBMS_OUTPUT.PUT_LINE('Total Amount: ' || v_total_amount);
        DBMS_OUTPUT.PUT_LINE('------------------------------------------');
    END IF;

    -- Close the cursor
    CLOSE GenerateMonthlyStatements;

EXCEPTION
    WHEN OTHERS THEN
        -- Handle exceptions
        IF GenerateMonthlyStatements%ISOPEN THEN
            CLOSE GenerateMonthlyStatements;
        END IF;
        RAISE;

END;
/
