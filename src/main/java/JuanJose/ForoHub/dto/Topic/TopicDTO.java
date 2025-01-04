package JuanJose.ForoHub.dto.Topic;


import JuanJose.ForoHub.dto.Category.CategoryDetailsDTO;
import JuanJose.ForoHub.dto.Response.DTOResponse;
import JuanJose.ForoHub.model.Topic;
import JuanJose.ForoHub.model.TopicStatus;
import JuanJose.ForoHub.model.TopicType;
import JuanJose.ForoHub.utils.ConverterData;

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
