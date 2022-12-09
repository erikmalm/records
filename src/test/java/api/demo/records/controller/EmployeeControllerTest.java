package api.demo.records.controller;

import api.demo.records.exception.RecordsFieldValueIsNullException;
import api.demo.records.exception.RecordsFieldValueMissingException;
import api.demo.records.model.Employee;
import api.demo.records.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeControllerTest {

    EmployeeController controller = new EmployeeController(new EmployeeRepository());

    String john = "John";
    String doe = "Doe";
    String johnDoeEmail = "john@doe.com";
    String nullString = null;


    String empty = "";


    /**
     * Placeholders for testing
     */
    Employee employeeJohnDoeWithCorrectDetails = new Employee(john, doe, johnDoeEmail);
    Employee employeeJohnDoeWithMissingEmail = new Employee(john, doe, empty);

    Employee employeeJohnDoeWithNullEmail = new Employee(john, doe, nullString);

    @Test
    void createNewEmployee() {
        assertNotNull(controller.create(employeeJohnDoeWithCorrectDetails), "Something went wrong, employee not created properly");
    }

    @Test
    void createNewEmployeeWithMissingEmail() {

        Throwable exception = assertThrows(
                RecordsFieldValueMissingException.class, () -> {
                    controller.create(employeeJohnDoeWithMissingEmail);
                });
        assertEquals("Field values can not be empty", exception.getMessage());
    }

    @Test
    void createNewEmployeeWithNullEmail() {
        Throwable exception = assertThrows(
                RecordsFieldValueIsNullException.class, () -> {
                    controller.create(employeeJohnDoeWithNullEmail);
                });
        assertEquals("Field values can not be null", exception.getMessage());
    }
}
