package JuanJose.ForoHub.dto.Course;

import jakarta.validation.constraints.NotBlank;

public record UpdateCourseDTO(
        @NotBlank
        String name,
        @NotBlank
        String description
) {
}
