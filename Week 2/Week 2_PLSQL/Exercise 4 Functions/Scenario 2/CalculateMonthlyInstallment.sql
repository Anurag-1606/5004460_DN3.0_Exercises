CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount    IN NUMBER,
    p_interest_rate  IN NUMBER,
    p_loan_duration  IN NUMBER
) 
RETURN NUMBER 
IS
    v_monthly_installment NUMBER;
    v_monthly_interest_rate NUMBER;
    v_total_installments NUMBER;
BEGIN
    -- Calculate the monthly interest rate
    v_monthly_interest_rate := p_interest_rate / 12 / 100;
    
    -- Calculate the total number of monthly installments
    v_total_installments := p_loan_duration * 12;
    
    -- Calculate the monthly installment using the formula
    v_monthly_installment := p_loan_amount * v_monthly_interest_rate * POWER(1 + v_monthly_interest_rate, v_total_installments) / (POWER(1 + v_monthly_interest_rate, v_total_installments) - 1);
    
    RETURN v_monthly_installment;
END CalculateMonthlyInstallment;
/
