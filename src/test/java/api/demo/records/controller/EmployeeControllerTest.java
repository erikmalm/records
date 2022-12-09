package api.demo.records.controller;

import api.demo.records.exception.RecordsFieldValueIsNullException;
import api.demo.records.exception.RecordsFieldValueMissingException;
import api.demo.records.model.Employee;
import api.demo.records.repository.EmployeeRepository;
import api.demo.records.service.EmployeeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeControllerTest {

    EmployeeController controller = new EmployeeController(new EmployeeService(new EmployeeRepository()));

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
        assertEquals("Email is required", exception.getMessage());
    }

    @Test
    void createNewEmployeeWithNullEmail() {
        Throwable exception = assertThrows(
                RecordsFieldValueMissingException.class, () -> {
                    controller.create(employeeJohnDoeWithNullEmail);
                });
        assertEquals("Email is required", exception.getMessage());
    }
}
