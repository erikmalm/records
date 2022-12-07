package api.demo.records.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record Employee(
        @NotEmpty
        String firstName,

        @NotEmpty
        String lastName,
        @NotEmpty
        @Email
        String email) {

}
