package api.demo.records.controller;

import api.demo.records.model.Employee;
import api.demo.records.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /**
     * Returns a list of all employees.
     *
     * @return a list of all employees
     */

    @GetMapping
    public List<Employee> findAll() {
        return service.getAllEmployees();
    }

    /**
     * Returns the employee with the specified email address.
     *
     * @param email the email address of the employee to retrieve
     * @return the employee with the specified email address
     */

    @GetMapping("/{email}")
    public Employee findByEmail(@PathVariable String email) {
        return service.findEmployeeByEmail(email);
    }

    /**
     * Creates a new employee.
     *
     * @param employee the employee to create
     * @return the created employee
     */

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee create(@RequestBody Employee stream) {
        return service.addEmployee(stream.firstName(), stream.lastName(),stream.email());
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
