package api.demo.records.repository;

import api.demo.records.exception.RecordsEmailAlreadyExistsException;
import api.demo.records.exception.RecordsFieldValueIsNullException;
import api.demo.records.exception.RecordsFieldValueMissingException;
import api.demo.records.model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeRepositoryTest {


     EmployeeRepository repository = new EmployeeRepository();


    String john = "John";
    String doe = "Doe";

    String jane = "Jane";
    String empty = "";
    String johnDoeEmail = "john@doe.com";

    String nullString = null;

    @Test
    void testEmailIsEmpty() {
        Throwable exception = assertThrows(
                RecordsFieldValueMissingException.class, () -> {
                    repository.create(new Employee(john, doe, empty));
                });
        assertEquals("Field values can not be empty", exception.getMessage());
    }

    @Test
    void testEmailIsNull() {
        Throwable exception = assertThrows(
                RecordsFieldValueIsNullException.class, () -> {
                    repository.create(new Employee(john, doe, nullString));
                });
        assertEquals("Field values can not be null", exception.getMessage());
    }

    @Test
    void testDuplicateEmail() {

        repository.create(new Employee(john, doe, johnDoeEmail));

        Throwable exception = assertThrows(
                RecordsEmailAlreadyExistsException.class, () -> {
                    repository.create(new Employee(jane, doe, johnDoeEmail));
                });
        assertEquals("Email already exists in memory", exception.getMessage());

    }

}
