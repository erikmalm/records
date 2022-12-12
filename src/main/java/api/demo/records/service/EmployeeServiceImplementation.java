package api.demo.records.service;

import api.demo.records.exception.RecordsEmployeeNotFoundException;
import api.demo.records.exception.RecordsFieldValueMissingException;
import api.demo.records.model.Employee;
import api.demo.records.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

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

    @Override
    public List<Employee> getAllEmployees() {
        // retrieve all employees from the database
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        // validate the input data and return null if invalid
        if (email == null || email.trim().isEmpty()) {
            return null;
        }
        return employeeRepository.findByEmail(email);
    }

    @Override
    public void removeEmployee(String email) {
        Employee employee = employeeRepository.findByEmail(email);

        if (employee == null) {
            throw new RecordsEmployeeNotFoundException("Employee not found");
        }
        employeeRepository.delete(employee.email());
    }
}
