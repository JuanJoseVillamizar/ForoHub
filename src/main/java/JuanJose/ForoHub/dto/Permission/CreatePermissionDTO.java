package JuanJose.ForoHub.dto.Permission;

import jakarta.validation.constraints.NotBlank;

public record CreatePermissionDTO(
        @NotBlank
        String name,
        String description
) {
}
