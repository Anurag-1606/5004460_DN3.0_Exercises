DELIMITER $$

CREATE PROCEDURE TransferFunds(
    IN source_account_id INT,
    IN destination_account_id INT,
    IN transfer_amount DECIMAL(10,2)
)
BEGIN
    DECLARE insufficient_balance EXCEPTION;
    
    -- Start a transaction
    START TRANSACTION;
    
    -- Check if the source account has sufficient balance
    IF (SELECT balance FROM accounts WHERE account_id = source_account_id) < transfer_amount THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Insufficient balance';
    ELSE
        -- Deduct the amount from the source account
        UPDATE accounts
        SET balance = balance - transfer_amount
        WHERE account_id = source_account_id;

        -- Add the amount to the destination account
        UPDATE accounts
        SET balance = balance + transfer_amount
        WHERE account_id = destination_account_id;
    END IF;
    
    -- Commit the transaction
    COMMIT;
END$$

DELIMITER ;
