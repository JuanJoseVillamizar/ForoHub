package JuanJose.ForoHub.dto.Topic;

import JuanJose.ForoHub.model.TopicStatus;
import JuanJose.ForoHub.model.TopicType;
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
        @NotNull
        Long idUser,
        Long idCourse
) {
}
