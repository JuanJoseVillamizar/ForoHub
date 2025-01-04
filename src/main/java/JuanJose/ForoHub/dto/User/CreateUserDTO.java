package JuanJose.ForoHub.dto.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateUserDTO(
        @NotBlank
        String name,
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                message = "The email must be a valid domain.")
        String email,
        @NotBlank
        String password,
        @NotNull
        Long idProfile
        ) {
}
