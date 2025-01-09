package juan.forohub.dto.category;

import juan.forohub.entities.Category;

public record ResponseCategoryDTO(
        Long id,
        String name
) {
    public ResponseCategoryDTO(Category category) {
        this(category.getId(), category.getName());
    }

}
