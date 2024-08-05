1.
Exercise 1: Control Structures

BEGIN
    FOR customer_rec IN (
        SELECT customer_id, age, loan_id, current_interest_rate
        FROM customers
        JOIN loans ON customers.customer_id = loans.customer_id
    ) LOOP
        IF customer_rec.age > 60 THEN
            UPDATE loans
            SET current_interest_rate = current_interest_rate - 0.01
            WHERE loan_id = customer_rec.loan_id;
            
            DBMS_OUTPUT.PUT_LINE('Applied 1% discount for loan ID: ' || customer_rec.loan_id || ' for customer ID: ' || customer_rec.customer_id);
        END IF;
    END LOOP;
END;


BEGIN
    FOR customer_rec IN (
        SELECT customer_id, balance
        FROM customers
    ) LOOP
        IF customer_rec.balance > 10000 THEN
            UPDATE customers
            SET is_vip = TRUE
            WHERE customer_id = customer_rec.customer_id;
            
            DBMS_OUTPUT.PUT_LINE('Customer ID: ' || customer_rec.customer_id || ' promoted to VIP status.');
        END IF;
    END LOOP;
END;

BEGIN
    FOR loan_rec IN (
        SELECT loan_id, customer_id, due_date
        FROM loans
        WHERE due_date BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
       
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan ID ' || loan_rec.loan_id || ' for customer ID ' || loan_rec.customer_id || ' is due on ' || loan_rec.due_date);
    END LOOP;
END;



2.
Exercise 2: Error Handling


CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_amount IN NUMBER
) AS
    v_from_balance NUMBER;
    v_to_balance NUMBER;
BEGIN
 
    SAVEPOINT before_transfer;

    SELECT balance INTO v_from_balance
    FROM accounts
    WHERE account_id = p_from_account_id;
    
    SELECT balance INTO v_to_balance
    FROM accounts
    WHERE account_id = p_to_account_id;

    IF v_from_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in account ' || p_from_account_id);
    END IF;

    UPDATE accounts
    SET balance = balance - p_amount
    WHERE account_id = p_from_account_id;

    UPDATE accounts
    SET balance = balance + p_amount
    WHERE account_id = p_to_account_id;

  
    COMMIT;
    
    DBMS_OUTPUT.PUT_LINE('Funds transferred successfully from account ' || p_from_account_id || ' to account ' || p_to_account_id);

EXCEPTION
    WHEN OTHERS THEN
       
        ROLLBACK TO before_transfer;


CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employee_id IN NUMBER,
    p_percentage IN NUMBER
) AS
    v_current_salary NUMBER;
BEGIN
   
    SELECT salary INTO v_current_salary
    FROM employees
    WHERE employee_id = p_employee_id;

    UPDATE employees
    SET salary = salary * (1 + p_percentage / 100)
    WHERE employee_id = p_employee_id;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Salary updated successfully for employee ID: ' || p_employee_id);

EXCEPTION
    WHEN NO_DATA_FOUND THEN
   
        DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_employee_id || ' does not exist.');
    WHEN OTHERS THEN
      
        DBMS_OUTPUT.PUT_LINE('Error updating salary: ' || SQLERRM);
END UpdateSalary;
      
        DBMS_OUTPUT.PUT_LINE('Error during fund transfer: ' || SQLERRM);
END SafeTransferFunds;


CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_balance IN NUMBER
) AS
BEGIN
    BEGIN
       
        INSERT INTO customers (customer_id, name, balance)
        VALUES (p_customer_id, p_name, p_balance);

        COMMIT;
        
        DBMS_OUTPUT.PUT_LINE('Customer added successfully with ID: ' || p_customer_id);

    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
          
            DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_customer_id || ' already exists.');
            ROLLBACK;
        WHEN OTHERS THEN
          
            DBMS_OUTPUT.PUT_LINE('Error adding customer: ' || SQLERRM);
            ROLLBACK;
    END;
END AddNewCustomer;



3.
Exercise 3: Stored Procedures



CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    v_account_id NUMBER;
    v_current_balance NUMBER;
BEGIN
  
    FOR account_rec IN (
        SELECT account_id, balance
        FROM accounts
        WHERE account_type = 'Savings'
    ) LOOP
        v_account_id := account_rec.account_id;
        v_current_balance := account_rec.balance;

       
        UPDATE accounts
        SET balance = v_current_balance * 1.01
        WHERE account_id = v_account_id;

        DBMS_OUTPUT.PUT_LINE('Updated balance for savings account ID: ' || v_account_id);
    END LOOP;

  
    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Monthly interest processed for all savings accounts.');
EXCEPTION
    WHEN OTHERS THEN
       
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error processing monthly interest: ' || SQLERRM);
END ProcessMonthlyInterest;


CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department_id IN NUMBER,
    p_bonus_percentage IN NUMBER
) AS
BEGIN
   
    UPDATE employees
    SET salary = salary * (1 + p_bonus_percentage / 100)
    WHERE department_id = p_department_id;

  
    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Salaries updated with a bonus of ' || p_bonus_percentage || '% for department ID: ' || p_department_id);
EXCEPTION
    WHEN OTHERS THEN
      
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating employee bonus: ' || SQLERRM);
END UpdateEmployeeBonus;

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_amount IN NUMBER
) AS
    v_from_balance NUMBER;
BEGIN
   
    SELECT balance INTO v_from_balance
    FROM accounts
    WHERE account_id = p_from_account_id;

    IF v_from_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in account ' || p_from_account_id);
    END IF;

 
    UPDATE accounts
    SET balance = balance - p_amount
    WHERE account_id = p_from_account_id;

    UPDATE accounts
    SET balance = balance + p_amount
    WHERE account_id = p_to_account_id;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount || ' from account ID: ' || p_from_account_id || ' to account ID: ' || p_to_account_id);
EXCEPTION
    WHEN OTHERS THEN
   
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error during fund transfer: ' || SQLERRM);
END TransferFunds;


4.
Exercise 4: Functions





