package JuanJose.ForoHub.dto.Category;

public record CategoryDetailsDTO(
        Long categoryId,
        String categoryName,
        Long subCategoryId,
        String subCategoryName,
        Long courseId,
        String courseName
) {}
