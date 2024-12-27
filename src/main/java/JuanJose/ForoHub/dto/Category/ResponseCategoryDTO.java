package JuanJose.ForoHub.dto.Category;

import JuanJose.ForoHub.model.Category;

public record ResponseCategoryDTO(
        Long id,
        String name
) {
    public ResponseCategoryDTO(Category category) {
        this(category.getId(), category.getName());
    }

}
