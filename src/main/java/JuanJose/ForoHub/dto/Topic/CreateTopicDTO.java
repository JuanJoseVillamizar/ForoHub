package JuanJose.ForoHub.dto.Topic;

import JuanJose.ForoHub.entities.TopicStatus;
import JuanJose.ForoHub.entities.TopicType;
import JuanJose.ForoHub.utils.TopicTypeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CreateTopicDTO(
        @NotBlank
        String title,
        @NotNull
                @JsonDeserialize(using = TopicTypeDeserializer.class)
        TopicType type,
        @NotBlank
        String message,
        TopicStatus status,
        String idCourse
) {
}
