package juan.forohub.dto.topic;

import juan.forohub.dto.category.CategoryDetailsDTO;
import juan.forohub.dto.user.UserDTO;
import juan.forohub.entities.TopicStatus;

import java.time.LocalDateTime;

public record TopicDetailsDTO(
        Long id,
        String title,
        TopicStatus status,
        LocalDateTime creationDate,
        UserDTO author,
        int numberOfResponses,
        CategoryDetailsDTO categoryDetailsDTO
) {
}
