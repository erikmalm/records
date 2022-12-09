package api.demo.records.repository;

import api.demo.records.exception.RecordsEmailAlreadyExistsException;
import api.demo.records.exception.RecordsFieldValueIsNullException;
import api.demo.records.exception.RecordsFieldValueMissingException;
import api.demo.records.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeRepositoryTest {


     EmployeeRepository employeeRepository;

     @BeforeEach
     public void setup() {
         this.employeeRepository = new EmployeeRepository();
     }

    // Variables used in testing
    String john = "John";
    String doe = "Doe";
    String jane = "Jane";
    String johnDoeEmail = "john@doe.com";
    String janeDoeEmail = "jane@doe.com";


    @Test
    void testEmailIsEmpty() {
        //Act and assert
        Throwable exception = assertThrows(
                RecordsFieldValueMissingException.class, () -> {
                    employeeRepository.create(new Employee(john, doe, ""));
                });
        assertEquals("Field values can not be empty", exception.getMessage());
    }

    @Test
    void testEmailIsNull() {
        //Act and assert
        Throwable exception = assertThrows(
                RecordsFieldValueIsNullException.class, () -> {
                    employeeRepository.create(new Employee(john, doe, null));
                });
        assertEquals("Field values can not be null", exception.getMessage());
    }

    @Test
    void testDuplicateEmail() {
        //Arrange
        employeeRepository.create(new Employee(john, doe, johnDoeEmail));

        //Act and assert
        Throwable exception = assertThrows(
                RecordsEmailAlreadyExistsException.class, () -> {
                    employeeRepository.create(new Employee(jane, doe, johnDoeEmail));
                });
        assertEquals("Email already exists in memory", exception.getMessage());

    }

    @Test
    public void testFindAll() {
        //Arrange
        List<Employee> expectedEmployees = Arrays.asList(
                new Employee(jane,doe,janeDoeEmail),
                new Employee(john,doe,johnDoeEmail)
        );

        for (Employee e : expectedEmployees) employeeRepository.create(e);

        //Act
        List<Employee> actualEmployees = employeeRepository.findAll();

        //Assert
        assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    public void testFindByEmail() {
        //Arrange
        Employee expectedEmployee = new Employee(john, doe, johnDoeEmail);
        employeeRepository.create(expectedEmployee);

        //Act
        Employee actualEmployee = employeeRepository.findByEmail(johnDoeEmail);

        //Assert
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void testFindByEmailNotFound() {
        //Act and assert
        assertEquals(null, employeeRepository.findByEmail(johnDoeEmail));
    }

    @Test
    public void testCreate() {
        //Arrange
        Employee expectedEmployee = new Employee(jane, doe, janeDoeEmail);

        //Act
        Employee actualEmployee = employeeRepository.create(expectedEmployee);

        //Assert
        assertEquals(expectedEmployee, actualEmployee);
        assertEquals(expectedEmployee, employeeRepository.findByEmail(janeDoeEmail));
    }

    @Test
    public void testCreate_nullEmployeeValues() {
        // Arrange
        Employee employee = new Employee(null, null, null);

        //Act and assert
        Throwable exception = assertThrows(
                RecordsFieldValueIsNullException.class, () -> {
                    employeeRepository.create(employee);
                });
        assertEquals("Field values can not be null", exception.getMessage());
    }

    @Test
    public void testDelete() {
        // Arrange
        Employee employee = new Employee(john, doe, johnDoeEmail);

        // Act
        employeeRepository.create(new Employee(john, doe, johnDoeEmail));
        employeeRepository.delete(employee.email());
        List<Employee> employees = employeeRepository.findAll();

        // Assert
        assertEquals(0, employees.size(), "Something went wrong, employee size: " + employees.size());
    }
}
