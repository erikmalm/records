package api.demo.records.repository;

import api.demo.records.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeRepository {

    List<Employee> streams = new ArrayList<>();
    public List<Employee> findAll() {
        return streams;
    }

    /**
     * Searches for an employee based on their e-mail address. Returns null if
     * no employee found with that e-mail address.
     * @param email The e-mail address to search for
     * @return The employee with that e-mail
     */

    public Employee findByEmail(String email) {
        return streams.stream().filter(employee -> employee.email().equals(email)).findFirst().orElse(null);
    }


    /**
     * Creates a new employee, based on incoming stream with
     *  employee details
     * @param stream The details of the new employee
     * @return The details of the added employee
     */

    public Employee create(Employee stream) {

        if (anyEmployeeValueIsNull(stream)) {
            throw new IllegalArgumentException("Field values can not be null");
        } else if (anyEmployeeValueIsEmpty(stream)) {
            throw new IllegalArgumentException("Field values can not be empty");
        }

        if (findByEmail(stream.email()) != null) {
            throw new RuntimeException("Email already exists in memory");
        }

        streams.add(stream);
        return stream;
    }

    /**
     * Update the current employee with new information. Throws exceptions if:
     * - New employee details are not valid
     * - Email to search for is empty or null
     * - Trying to change e-mail to an existing users e-mail address
     * @param stream The new (updated) details
     * @param email The email of the user to update
     */

    public void update(Employee stream, String email) {

        if (email == null) {
            throw new RuntimeException("Email can't be null");
        } else if (email.equals("")) {
            throw new IllegalArgumentException("Email can't be empty");
        } else if (anyEmployeeValueIsNull(stream)) {
            throw new IllegalArgumentException("Field values can not be null");
        } else if (anyEmployeeValueIsEmpty(stream)) {
            throw new IllegalArgumentException("Field values can not be empty");
        }

        if (!Objects.equals(stream.email(), email)) {
            if (findByEmail(stream.email()) != null) {
                throw new RuntimeException("Trying to update to existing e-mail address");
            }
        }

        Employee existing = streams.stream().filter(s -> s.email().equals(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Stream not found"));
        int i = streams.indexOf(existing);
        streams.set(i, stream);
    }

    /**
     * Call to delete the requested employee by e-mail
     * @param email The email of the user to delete
     */

    public void delete(String email) {
        streams.removeIf(employee -> employee.email().equals(email));
    }

    /**
     * Helper method for repository. Checks if any employee detail is null
     * @param details The current employee details
     * @return True if any employee detail is null, otherwise false
     */
    private boolean anyEmployeeValueIsNull(Employee details) {
        return details.firstName() == null
                || details.lastName() == null
                || details.email() == null;
    }

    /**
     * Helper method for repository. Checks if any employee detail is empty
     * @param details The current Employee details
     * @return True if any employee detail is empty, otherwise false
     */
    private boolean anyEmployeeValueIsEmpty(Employee details) {
        return Objects.equals(details.firstName(), "")
                || Objects.equals(details.lastName(), "")
                || Objects.equals(details.email(), "");
    }

}
