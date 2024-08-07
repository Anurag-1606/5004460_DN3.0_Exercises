CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (LogID, TransactionID, Action, ActionDate)
  VALUES (AuditLog_seq.NEXTVAL, :NEW.TransactionID, 'INSERT', SYSDATE);
END;
/
