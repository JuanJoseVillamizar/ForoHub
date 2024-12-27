package JuanJose.ForoHub.dto.SubCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSubCategoryDTO(
        @NotBlank
        String name,
        @NotNull
        Long idCategory
) {
    public CreateSubCategoryDTO(String name, Long idCategory) {
        this.name = name;
        this.idCategory=idCategory;
    }
}
