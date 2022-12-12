package api.demo.records.controller;

import api.demo.records.exception.RecordsEmployeeNotFoundException;
import api.demo.records.exception.RecordsFieldValueMissingException;
import api.demo.records.model.Employee;
import api.demo.records.repository.EmployeeRepository;
import api.demo.records.service.EmployeeServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class EmployeeControllerTest {

    private EmployeeServiceImplementation employeeServiceImplementation;
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        employeeServiceImplementation = new EmployeeServiceImplementation(new EmployeeRepository());
        employeeController = new EmployeeController(employeeServiceImplementation);
    }

    // Variables used in testing
    String john = "John";
    String jane = "Jane";
    String doe = "Doe";
    String johnDoeEmail = "john@doe.com";
    String janeDoeEmail = "jane@doe.com";
    String empty = "";


    /**
     * Placeholders for testing
     */
    Employee employeeJohnDoeWithCorrectDetails = new Employee(john, doe, johnDoeEmail);
    Employee employeeJohnDoeWithMissingEmail = new Employee(john, doe, empty);

    Employee employeeJohnDoeWithNullEmail = new Employee(john, doe, null);

    @Test
    void createNewEmployee() {
        //Act and assert
        assertNotNull(employeeController
                        .create(employeeJohnDoeWithCorrectDetails),
                "Something went wrong, employee not created properly");
    }

    @Test
    void createNewEmployeeWithMissingEmail() {
        //Act and assert
        Throwable exception = assertThrows(
                RecordsFieldValueMissingException.class, () -> {
                    employeeController.create(employeeJohnDoeWithMissingEmail);
                });
        assertEquals("Email is required", exception.getMessage());
    }

    @Test
    void createNewEmployeeWithNullEmail() {
        //Act and assert
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
        for (Employee e : expectedEmployees) employeeServiceImplementation.addEmployee(e.firstName(),e.lastName(),e.email());

        //Act
        List<Employee> actualEmployees = employeeController.findAll();

        //Assert
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void testFindEmployeeByEmail() {
        //Arrange
        employeeServiceImplementation.addEmployee(john, doe, johnDoeEmail);

        //Act
        Employee actualEmployee = employeeController.findByEmail(johnDoeEmail);

        //Assert
        assertEquals(employeeJohnDoeWithCorrectDetails, actualEmployee);
    }

    @Test
    public void testDeleteEmployee() {
        //Arrange
        employeeServiceImplementation.addEmployee(john, doe, johnDoeEmail);

        //Act
        employeeController.delete(johnDoeEmail);

        //Assert
        assertEquals(0, employeeServiceImplementation.getAllEmployees().size());
    }

    @Test
    public void testDeleteEmployeeNotFound() {
        //Act and assert
        Throwable exception = assertThrows(
                RecordsEmployeeNotFoundException.class, () -> {
                    employeeController.delete(johnDoeEmail);
                });
        assertEquals("Employee not found", exception.getMessage());
    }



}
