package juan.forohub.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDTO(
        @NotBlank
        String name
) {
}
