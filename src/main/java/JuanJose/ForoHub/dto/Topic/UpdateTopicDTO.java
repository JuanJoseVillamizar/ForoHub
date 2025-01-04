package JuanJose.ForoHub.dto.Topic;

import JuanJose.ForoHub.model.TopicStatus;
import JuanJose.ForoHub.model.TopicType;
import JuanJose.ForoHub.utils.TopicTypeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTopicDTO(
        String title,
        String message
) {
}
