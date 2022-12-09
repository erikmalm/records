package api.demo.records.service;

import api.demo.records.exception.RecordsFieldValueMissingException;
import api.demo.records.model.Employee;
import api.demo.records.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Service class for managing employees.
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Constructs a new EmployeeService instance.
     *
     * @param employeeRepository the EmployeeRepository instance to use for interacting with the repository layer
     */
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
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
     * @return a list of all Employee entities
     */
    public List<Employee> getAllEmployees() {
        // retrieve all employees from the database
        return employeeRepository.findAll();
    }

    /**
     * Finds an employee by email.
     *
     * @param email the email of the employee to find
     * @return the Employee entity with the specified email,
     * or null if no such employee exists
     */

    public Employee findEmployeeByEmail(String email) {
        // validate the input data and return null if invalid
        if (email == null || email.trim().isEmpty()) {
            return null;
        }
        return employeeRepository.findByEmail(email);
    }

    /**
     * Deletes the employee with the specified email address.
     *
     * @param email the email address of the employee to delete
     */
    public void removeEmployee(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee != null) {
            employeeRepository.delete(employee.email());
        }
    }


}



