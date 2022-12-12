package api.demo.records.controller;

import api.demo.records.model.Employee;
import api.demo.records.service.EmployeeServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Controller class for handling HTTP requests related to Employee records.
 * Uses EmployeeServiceImplementation to perform business logic operations.
 */

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class EmployeeController {

    private final EmployeeServiceImplementation service;

    /**
     * Constructor for injecting an instance of EmployeeServiceImplementation.
     *
     * @param service  an instance of EmployeeServiceImplementation
     */
    public EmployeeController(EmployeeServiceImplementation service) {
        this.service = service;
    }

    /**
     * Returns a list of all Employee records.
     *
     * @return a List of Employee objects
     */
    @GetMapping
    public List<Employee> findAll() {
        return service.getAllEmployees();
    }

    /**
     * Returns the Employee record with the given email address.
     *
     * @param email the email address of the Employee to retrieve
     * @return the Employee with the given email address, or null if no such Employee exists
     */
    @GetMapping("/{email}")
    public Employee findByEmail(@PathVariable String email) {
        return service.findEmployeeByEmail(email);
    }

    /**
     * Creates a new Employee record.
     *
     * @param stream the Employee object to create
     * @return the newly-created Employee
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee create(@RequestBody Employee stream) {
            return service.addEmployee(stream.firstName(), stream.lastName(), stream.email());
    }

    /**
     * Deletes the employee with the specified email address.
     *
     * @param email the email address of the employee to delete
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{email}")
    public void delete(@PathVariable String email) {
        service.removeEmployee(email);
    }
}
