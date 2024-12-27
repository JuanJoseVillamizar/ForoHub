package JuanJose.ForoHub.dto.Category;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDTO(
        @NotBlank
        String name
) {
    public CreateCategoryDTO(String name) {
        this.name = name;
    }
}
