package api.demo.records.service;

import api.demo.records.exception.RecordsFieldValueMissingException;
import api.demo.records.model.Employee;

import java.util.List;

/**
 * An interface for a service that manages {@link Employee} records
 */

public interface EmployeeService {

    /**
     * Adds a new employee to the records database.
     *
     * @param firstName the employee's first name
     * @param lastName the employee's last name
     * @param email the employee's email address
     * @return the added {@link Employee}
     * @throws RecordsFieldValueMissingException if any of the required fields (first name, last name, or email) are missing
     */
    Employee addEmployee(String firstName, String lastName, String email) throws RecordsFieldValueMissingException;

    /**
     * Returns a list of all employees in the records database.
     *
     * @return a list of all employees
     */
    List<Employee> getAllEmployees();

    /**
     * Returns the employee with the specified email address, or {@code null} if no such employee exists.
     *
     * @param email the email address to search for
     * @return the employee with the specified email address, or {@code null} if no such employee exists
     */
    Employee findEmployeeByEmail(String email);

    /**
     * Removes the employee with the specified email address from the records database.
     *
     * @param email the email address of the employee to remove
     */
    void removeEmployee(String email);

}
