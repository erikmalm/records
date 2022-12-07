package api.demo.records.controller;

import api.demo.records.model.Employee;
import api.demo.records.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeRepository repository;

    /**
     * Creates a new instance of the controller with the repository, containing the employee records
     * currently stored in memory. This is implicitly autowired, since it is no longer necessary to
     * specify the @Autowired annotation for constructor based dependency injection.
     * @param repository the repository for local memory
     */

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * Find all employees currently stored in memory
     * GET -> http://localhost:8080/api/
     * @return all employees in memory
     */

    // http://localhost:8080/api/
    @GetMapping
    public List<Employee> findAll() {
        return repository.findAll();
    }

    /**
     * Find employee by email.
     * GET -> http://localhost:8080/api/{email}
     * @param email of the queried employee
     * @return the employee with the applied email
     */

    @GetMapping("/{email}")
    public Employee findByEmail(@PathVariable String email) {
        return repository.findByEmail(email);
    }

    /**
     * Create a new employee and store on local memory.
     * Also returns HTTP 200 if successfully created.
     * POST -> http://localhost:8080/api/{}
     * @param stream employee details to store
     * @return the updated repository.
     */

    // POST http://localhost:8080/api/
    // Return 200 status code
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee create(@Valid @RequestBody Employee stream) {
        return repository.create(stream);
    }

    /**
     * Deletes the employee with the applied e-mail
     * Also returns HTTP 200 if successfully deleted.
     * DELETE -> http://localhost:8080/api/{email}
     * @param email the email of the employee record to delete
     */

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{email}")
    public void delete(@PathVariable String email) {
        repository.delete(email);
    }
}
