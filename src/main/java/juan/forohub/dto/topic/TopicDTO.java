package juan.forohub.dto.topic;


import juan.forohub.dto.response.DTOResponse;
import juan.forohub.entities.Topic;
import juan.forohub.entities.TopicStatus;
import juan.forohub.entities.TopicType;
import juan.forohub.utils.ConverterData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record TopicDTO(
        Long id,
        String title,
        String message,
        TopicStatus status,
        TopicType type,
        LocalDateTime creationDate,
        Long authorId,
        List<DTOResponse> responses
) {
    public TopicDTO(Topic topic) {
        this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getStatus(),
                topic.getType(),
                topic.getCreationDate(),
                topic.getAuthor().getId(),
                topic.getResponses().stream()
                        .map(ConverterData::convertToDTOResponse)
                        .collect(Collectors.toList()));

    }
}
