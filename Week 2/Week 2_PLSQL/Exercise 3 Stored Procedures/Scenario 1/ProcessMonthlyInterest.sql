DELIMITER $$

CREATE PROCEDURE ProcessMonthlyInterest()
BEGIN
    -- Declare a variable to hold the interest rate
    DECLARE interest_rate DECIMAL(5,2) DEFAULT 0.01;

    -- Update the balance of each savings account
    UPDATE savings_accounts
    SET balance = balance + (balance * interest_rate);
END$$

DELIMITER ;
