package api.demo.records.controller;

import api.demo.records.exception.RecordsEmployeeNotFoundException;
import api.demo.records.exception.RecordsFieldValueMissingException;
import api.demo.records.model.Employee;
import api.demo.records.repository.EmployeeRepository;
import api.demo.records.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class EmployeeControllerTest {

    private EmployeeService employeeService;
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeService(new EmployeeRepository());
        employeeController = new EmployeeController(employeeService);
    }

    // Variables used in testing
    String john = "John";
    String jane = "Jane";
    String doe = "Doe";
    String johnDoeEmail = "john@doe.com";
    String janeDoeEmail = "jane@doe.com";
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
        assertNotNull(employeeController
                        .create(employeeJohnDoeWithCorrectDetails),
                "Something went wrong, employee not created properly");
    }

    @Test
    void createNewEmployeeWithMissingEmail() {
        Throwable exception = assertThrows(
                RecordsFieldValueMissingException.class, () -> {
                    employeeController.create(employeeJohnDoeWithMissingEmail);
                });
        assertEquals("Email is required", exception.getMessage());
    }

    @Test
    void createNewEmployeeWithNullEmail() {
        Throwable exception = assertThrows(
                RecordsFieldValueMissingException.class, () -> {
                    employeeController.create(employeeJohnDoeWithNullEmail);
                });
        assertEquals("Email is required", exception.getMessage());
    }


    @Test
    public void testGetEmployees() {
        //Arrange
        List<Employee> expectedEmployees = Arrays.asList(
                new Employee("John","Doe","john.doe@example.com"),
                new Employee("Jane","Doe","jane.doe@example.com")
        );
        for (Employee e : expectedEmployees) employeeService.addEmployee(e.firstName(),e.lastName(),e.email());

        //Act
        List<Employee> actualEmployees = employeeController.findAll();

        //Assert
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void testFindEmployeeByEmail() {
        //Arrange
        employeeService.addEmployee(john, doe, johnDoeEmail);

        //Act
        Employee actualEmployee = employeeController.findByEmail(johnDoeEmail);

        //Assert
        assertEquals(employeeJohnDoeWithCorrectDetails, actualEmployee);
    }

    @Test
    public void testDeleteEmployee() {
        //Arrange
        employeeService.addEmployee(john, doe, johnDoeEmail);

        //Act
        employeeController.delete(johnDoeEmail);

        //Assert
        assertEquals(0, employeeService.getAllEmployees().size());
    }

    @Test
    public void testDeleteEmployeeNotFound() {
        Throwable exception = assertThrows(
                RecordsEmployeeNotFoundException.class, () -> {
                    employeeController.delete(johnDoeEmail);
                });
        assertEquals("Employee not found", exception.getMessage());
    }



}
