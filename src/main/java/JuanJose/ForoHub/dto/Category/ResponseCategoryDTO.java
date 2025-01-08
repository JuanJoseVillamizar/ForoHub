package JuanJose.ForoHub.dto.Category;

import JuanJose.ForoHub.entities.Category;

public record ResponseCategoryDTO(
        Long id,
        String name
) {
    public ResponseCategoryDTO(Category category) {
        this(category.getId(), category.getName());
    }

}
