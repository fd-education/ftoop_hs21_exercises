package pva02.unittest1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDetailsTest {
    EmployeeDetails poorEmp, richEmp;

    @BeforeEach
    void setUp() {
        poorEmp = new EmployeeDetails();
        poorEmp.setName("John Doe");
        poorEmp.setAge(20);
        poorEmp.setMonthlySalary(4000);

        richEmp = new EmployeeDetails();
        richEmp.setName("Jane Doe");
        richEmp.setAge(30);
        richEmp.setMonthlySalary(12000);
    }

    @AfterEach
    void tearDown() {  }

    @Test
    void getNameTest() {
        assertEquals("John Doe", poorEmp.getName());
        assertEquals("Jane Doe", richEmp.getName());
    }

    @Test
    void getAgeTest() {
        assertEquals(20, poorEmp.getAge());
        assertEquals(30, richEmp.getAge());
    }

    @Test
    void getSalaryTest() {
        assertEquals(20, poorEmp.getAge());
        assertEquals(30, richEmp.getAge());
    }

    @Test
    void setNegativeMonthlySalaryTest(){
        try{
            EmployeeDetails excEmp = new EmployeeDetails();
            excEmp.setMonthlySalary(-1000);
        } catch(Exception e){
            assertTrue(e instanceof NegativeSalaryException);
        }
    }

    @Test
    void setNegativeMonthlySalaryTestAlternative() {
        EmployeeDetails excEmp = new EmployeeDetails();
        assertThrows(NegativeSalaryException.class, () -> excEmp.setMonthlySalary(-1000));
    }

    @Test
    void setNegativeMonthlySalaryTextTest() {
        EmployeeDetails excEmp = new EmployeeDetails();
        Exception text = assertThrows(NegativeSalaryException.class, () -> excEmp.setMonthlySalary(-1000));

        assertEquals("Negative salary not allowed.", text.getMessage());
    }
}