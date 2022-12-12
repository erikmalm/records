package api.demo.records.service;

import api.demo.records.exception.RecordsEmployeeNotFoundException;
import api.demo.records.exception.RecordsFieldValueMissingException;
import api.demo.records.model.Employee;
import api.demo.records.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceImplementationTest {

    EmployeeRepository employeeRepository;
    EmployeeServiceImplementation employeeServiceImplementation;

    @BeforeEach
    public void setUp() {
        employeeRepository = new EmployeeRepository();
        employeeServiceImplementation = new EmployeeServiceImplementation(employeeRepository);
    }

    // Variables used in testing
    String john = "John";
    String jane = "Jane";
    String doe = "Doe";
    String johnDoeEmail = "john@doe.com";
    String janeDoeEmail = "jane@doe.com";


    @Test
    public void testAddEmployee_addedEmployee() {
        // Arrange
        Employee employee = new Employee(john,doe,johnDoeEmail);

        // Act
        Employee addedEmployee = employeeServiceImplementation.addEmployee(employee.firstName(), employee.lastName(), employee.email());

        // Assert
        assertEquals(employee, addedEmployee);
        assertTrue(employeeRepository.findAll().contains(employee));
    }

    @Test
    public void testAddEmployee_missingEmployeeValues() {
        // Call the addEmployee() method on the service with missing input data
        Throwable exception = assertThrows(
                RecordsFieldValueMissingException.class, () -> {
                    employeeServiceImplementation.addEmployee(null, john,johnDoeEmail);
                });
        assertEquals("First name is required", exception.getMessage());
    }

    @Test
    public void testGetAllEmployees() {
        // Arrange
        List<Employee> expectedEmployees = Arrays.asList(
                new Employee(john,doe,johnDoeEmail),
                new Employee(jane,doe,janeDoeEmail)
        );

        for (Employee e : expectedEmployees)
            employeeServiceImplementation.addEmployee(e.firstName(),e.lastName(),e.email());


        // Act
        List<Employee> employees = employeeServiceImplementation.getAllEmployees();

        // Assert that the returned list contains the employees we added
        for (Employee e : expectedEmployees)
            assertTrue(employees.contains(e),"Employee seems to be missing");
    }
    @Test
    public void testFindEmployeeByEmail() {
        // Arrane
        Employee employee = new Employee(john,doe,johnDoeEmail);

        // Act
        employeeRepository.create(employee);
        Employee foundEmployee = employeeServiceImplementation.findEmployeeByEmail(employee.email());

        // Assert
        assertEquals(employee, foundEmployee);
    }

    @Test
    public void testRemoveEmployee() {
        // Arrange
        Employee employee = new Employee(john,doe,johnDoeEmail);

        // Act
        employeeRepository.create(employee);
        employeeServiceImplementation.removeEmployee(employee.email());
        List<Employee> employees = employeeRepository.findAll();

        // Assert that the list of employees is empty
        assertEquals(0, employees.size(),"Expected size = 0, was: " + employees.size());
    }

    @Test
    public void testRemoveEmployee_throwsException() {
        // Arrange
        Employee employee = new Employee(john,doe,johnDoeEmail);
        employeeRepository.create(employee);

        //Act and assert
        Throwable exception = assertThrows(
                RecordsEmployeeNotFoundException.class, () -> {
                    employeeServiceImplementation.removeEmployee(janeDoeEmail); // Note different email
                });
        assertEquals("Employee not found", exception.getMessage());
    }

}


