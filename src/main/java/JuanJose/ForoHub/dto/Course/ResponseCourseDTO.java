package JuanJose.ForoHub.dto.Course;

public record ResponseCourseDTO(
        Long id,
        String name,
        String description,
        Long categoryId,
        Long subCategoryId

) {
}
