DELIMITER $$

CREATE PROCEDURE UpdateEmployeeBonus(
    IN dept_id INT,
    IN bonus_percentage DECIMAL(5,2)
)
BEGIN
    -- Update the salary of each employee in the specified department
    UPDATE employees
    SET salary = salary + (salary * (bonus_percentage / 100))
    WHERE department_id = dept_id;
END$$

DELIMITER ;
