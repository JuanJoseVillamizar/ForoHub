package JuanJose.ForoHub.dto.Category;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryDTO(
        @NotBlank
        String name
) {
    public UpdateCategoryDTO(String name) {
        this.name = name;
    }
}
