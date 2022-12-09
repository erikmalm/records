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


    String john = "John";
    String doe = "Doe";

    String jane = "Jane";
    String empty = "";
    String johnDoeEmail = "john@doe.com";
    String janeDoeEmail = "jane@doe.com";

    String nullString = null;

    @Test
    void testEmailIsEmpty() {
        Throwable exception = assertThrows(
                RecordsFieldValueMissingException.class, () -> {
                    employeeRepository.create(new Employee(john, doe, empty));
                });
        assertEquals("Field values can not be empty", exception.getMessage());
    }

    @Test
    void testEmailIsNull() {
        Throwable exception = assertThrows(
                RecordsFieldValueIsNullException.class, () -> {
                    employeeRepository.create(new Employee(john, doe, nullString));
                });
        assertEquals("Field values can not be null", exception.getMessage());
    }

    @Test
    void testDuplicateEmail() {

        employeeRepository.create(new Employee(john, doe, johnDoeEmail));

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

}
