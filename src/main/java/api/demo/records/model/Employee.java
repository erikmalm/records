package api.demo.records.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

/**
 * Record of an employee. Contains all the reuired details
 * for the employee.
 * @param firstName The first name of the employee
 * @param lastName The last name of the employee.
 * @param email The e-mail of the employee (UNIQUE)
 */

public record Employee(
        @NotEmpty
        String firstName,

        @NotEmpty
        String lastName,
        @NotEmpty
        @Email
        String email) {

}
