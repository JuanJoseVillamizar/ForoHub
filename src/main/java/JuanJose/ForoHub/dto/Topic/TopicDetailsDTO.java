package JuanJose.ForoHub.dto.Topic;

import JuanJose.ForoHub.dto.Category.CategoryDetailsDTO;
import JuanJose.ForoHub.dto.User.UserDTO;
import JuanJose.ForoHub.model.TopicStatus;

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
