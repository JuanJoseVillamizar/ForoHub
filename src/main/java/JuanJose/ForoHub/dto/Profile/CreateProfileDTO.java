package JuanJose.ForoHub.dto.Profile;

import jakarta.validation.constraints.NotBlank;

public record CreateProfileDTO(
        @NotBlank
        String name,
        @NotBlank
        String description
) {
}
