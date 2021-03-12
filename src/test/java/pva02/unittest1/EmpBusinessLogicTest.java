package pva02.unittest1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpBusinessLogicTest {
    EmpBusinessLogic empLogic;
    EmployeeDetails poorEmp, richEmp;


    @BeforeEach
    void setUp() {
        empLogic = new EmpBusinessLogic();

        poorEmp = new EmployeeDetails();
        poorEmp.setMonthlySalary(4000);

        richEmp = new EmployeeDetails();
        richEmp.setMonthlySalary(12000);
    }

    @AfterEach
    void tearDown() {  }

    @Test
    void calculateYearlySalaryTest() {
        assertEquals(48000, empLogic.calculateYearlySalary(poorEmp));
        assertEquals(144000, empLogic.calculateYearlySalary(richEmp));
    }

    @Test
    void calculateAppraisalTest() {
        assertEquals(500, empLogic.calculateAppraisal(poorEmp));
        assertEquals(1000, empLogic.calculateAppraisal(richEmp));
    }
}