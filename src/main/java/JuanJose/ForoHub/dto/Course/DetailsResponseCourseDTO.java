package JuanJose.ForoHub.dto.Course;

import JuanJose.ForoHub.entities.Course;

public record DetailsResponseCourseDTO(
        Long id,
        String name,
        String description,
        String category,
        String subcategory
) {
    public DetailsResponseCourseDTO(Course course){
        this(course.getId(),course.getName(),course.getDescription(),
                course.getSubCategory().getCategory().getName(),
                course.getSubCategory().getName());
    }
}
