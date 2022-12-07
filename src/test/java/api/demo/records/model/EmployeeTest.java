package api.demo.records.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {



    @Test
    void createNewEmployee() {
        Employee employee = new Employee(
                "John",
                "Doe",
                "john@doe.com"
        );
        assertNotNull(employee);
        assertEquals("John",employee.firstName(), "Something went wrong. Name was: " + employee.firstName());
    }



}
