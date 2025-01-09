package juan.forohub.dto.course;

import juan.forohub.dto.topic.TopicDTO;

import java.util.List;

public record CourseDTO(
        Long id,
        String name,
        String description,
        List<TopicDTO> topics
) {
}
