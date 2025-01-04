package JuanJose.ForoHub.dto.Course;

import JuanJose.ForoHub.dto.Topic.TopicDTO;

import java.util.List;

public record CourseDTO(
        Long id,
        String name,
        String description,
        List<TopicDTO> topics
) {
}
