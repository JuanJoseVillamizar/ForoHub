package JuanJose.ForoHub.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(
       @NotBlank String email,
       @NotBlank String password
) {
}
