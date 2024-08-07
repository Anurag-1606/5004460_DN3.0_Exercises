CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) 
RETURN BOOLEAN 
IS
    v_balance NUMBER;
BEGIN
    -- Retrieve the balance of the specified account
    SELECT balance 
    INTO v_balance 
    FROM accounts 
    WHERE account_id = p_account_id;
    
    -- Check if the balance is sufficient
    IF v_balance >= p_amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- If no account is found, return FALSE
        RETURN FALSE;
END HasSufficientBalance;
/
