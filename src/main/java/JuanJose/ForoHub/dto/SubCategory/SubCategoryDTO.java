package JuanJose.ForoHub.dto.SubCategory;

import JuanJose.ForoHub.dto.Course.CourseDTO;
import JuanJose.ForoHub.model.Course;

import java.util.List;

public record SubCategoryDTO(
        Long id,
        String name,
        List<CourseDTO> courses
) {
}
