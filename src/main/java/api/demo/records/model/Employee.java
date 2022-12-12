package api.demo.records.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.UniqueElements;

/**
 * Record of an employee. Contains all the reuired details
 * for the employee.
 * @param firstName The first name of the employee
 * @param lastName The last name of the employee.
 * @param email The e-mail of the employee (UNIQUE)
 */

public record Employee(

        // These annotations have no practical use atm. Exceptions are handled manually.
        // However, they are kept for clarity in the code.

        @NotEmpty
        String firstName,

        @NotEmpty
        String lastName,
        @NotEmpty
        @Email
        @UniqueElements
        String email) {

}
