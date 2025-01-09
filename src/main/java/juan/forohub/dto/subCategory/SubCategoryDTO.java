package juan.forohub.dto.subCategory;

import juan.forohub.dto.course.CourseDTO;

import java.util.List;

public record SubCategoryDTO(
        Long id,
        String name,
        List<CourseDTO> courses
) {
}
