package MoEzwawi.BES6L5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotEmpty(message = "missing mandatory field")
        @Size(min = 3,max = 30, message = "invalid username")
        String username,
        @NotEmpty(message = "missing mandatory field")

        String name,
        @NotEmpty(message = "missing mandatory field")

        String surname,
        @NotEmpty(message = "missing mandatory field")
        @Email(message = "insert a valid email")
        String email) {
}
