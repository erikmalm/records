package api.demo.records.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @BeforeEach
    public void setUp() {

    }

    String john = "John";
    String doe = "Doe";
    String johnDoeEmail = "john@doe.com";


    @Test
    void createNewEmployee() {
        //Arrange
        Employee employee = new Employee(john,doe,johnDoeEmail);

        // Assert
        assertNotNull(employee);
        assertEquals("John",employee.firstName(), "Something went wrong. Name was: " + employee.firstName());
    }


    @Test
    public void testToString() {
        Employee e1 = new Employee(john,doe,johnDoeEmail);
        String expected = "Employee[firstName=John, lastName=Doe, email=john@doe.com]";
        assertEquals(expected, e1.toString(), "Not expected, actual was: " + e1.toString());
    }

    @Test
    public void testGetters() {
        Employee e = new Employee(john,doe,johnDoeEmail);
        assertEquals(john, e.firstName());
        assertEquals(doe, e.lastName());
        assertEquals(johnDoeEmail, e.email());
    }

}
