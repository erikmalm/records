package api.demo.records.service;

import api.demo.records.exception.RecordsEmployeeNotFoundException;
import api.demo.records.exception.RecordsFieldValueMissingException;
import api.demo.records.model.Employee;
import api.demo.records.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the EmployeeService interface.
 * Provides methods for managing employee records.
 */

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Constructs a new EmployeeServiceImplementation instance.
     *
     * @param employeeRepository the EmployeeRepository instance to use for interacting with the repository layer
     */

    @Autowired
    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Adds a new employee with the specified first name, last name, and email.
     *
     * @param firstName the first name of the employee
     * @param lastName  the last name of the employee
     * @param email     the email of the employee
     * @return the saved Employee entity
     * @throws RecordsFieldValueMissingException if any of the required input data is missing or invalid
     */

    @Override
    public Employee addEmployee(String firstName, String lastName, String email) throws RecordsFieldValueMissingException {
        // validate the input data and throw an exception if invalid
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new RecordsFieldValueMissingException("First name is required");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new RecordsFieldValueMissingException("Last name is required");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new RecordsFieldValueMissingException("Email is required");
        }

        // create a new Employee object with the input data
        Employee employee = new Employee(firstName, lastName, email);

        // save the employee to the database and return the saved entity
        return employeeRepository.create(employee);
    }

    /**
     * Retrieves all employees from the database.
     *
     * @return a list of Employee objects
     */

    @Override
    public List<Employee> getAllEmployees() {
        // retrieve all employees from the database
        return employeeRepository.findAll();
    }

    /**
     * Retrieves the Employee object associated with the given email address.
     * If the input is invalid (null or an empty string), returns null.
     *
     * @param email the email address of the Employee to retrieve
     * @return the Employee object associated with the given email address, or null if the input is invalid
     */

    @Override
    public Employee findEmployeeByEmail(String email) {
        // validate the input data and return null if invalid
        if (email == null || email.trim().isEmpty()) {
            return null;
        }
        return employeeRepository.findByEmail(email);
    }

    /**
     * Removes an employee from the employee repository.
     *
     * @param email The email of the employee to be removed.
     * @throws RecordsEmployeeNotFoundException If the employee with the given email is not found in the repository.
     */

    @Override
    public void removeEmployee(String email) {
        Employee employee = employeeRepository.findByEmail(email);

        if (employee == null) {
            throw new RecordsEmployeeNotFoundException("Employee not found");
        }
        employeeRepository.delete(employee.email());
    }
}
