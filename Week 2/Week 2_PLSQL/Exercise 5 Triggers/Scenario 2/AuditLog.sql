CREATE TABLE AuditLog (
    LogID NUMBER PRIMARY KEY,
    TransactionID NUMBER,
    Action VARCHAR2(50),
    ActionDate DATE
);
