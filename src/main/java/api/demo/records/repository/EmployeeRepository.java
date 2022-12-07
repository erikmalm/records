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

    public Employee findByEmail(String email) {
        return streams.stream().filter(employee -> employee.email().equals(email)).findFirst().orElse(null);
    }



    public Employee create(Employee stream) {

        if (anyValueIsNull(stream)) {
            throw new IllegalArgumentException("Field values can not be null");
        } else if (anyValueIsEmpty(stream)) {
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
     * @param stream the new details
     * @param email the email of the current user
     */

    public void update(Employee stream, String email) {

        if (email == null) {
            throw new RuntimeException("Email can't be null");
        } else if (email.equals("")) {
            throw new IllegalArgumentException("Email can't be empty");
        } else if (anyValueIsNull(stream)) {
            throw new IllegalArgumentException("Field values can not be null");
        } else if (anyValueIsEmpty(stream)) {
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
     * @param email the email of the user to delete
     */

    public void delete(String email) {
        streams.removeIf(employee -> employee.email().equals(email));
    }

    /**
     * Helper method for repository. Checks if any employee detail is null
     * @param details the current stream details
     * @return true if any employee detail is null, else false
     */
    private boolean anyValueIsNull(Employee details) {
        return details.firstName() == null
                || details.lastName() == null
                || details.email() == null;
    }

    /**
     * Helper method for repository. Checks if any employee detail is empty
     * @param details the current stream details
     * @returntrue if any employee detail is empty, else false
     */
    private boolean anyValueIsEmpty(Employee details) {
        return Objects.equals(details.firstName(), "")
                || Objects.equals(details.lastName(), "")
                || Objects.equals(details.email(), "");
    }

}
