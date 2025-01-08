package JuanJose.ForoHub.dto.Topic;

import JuanJose.ForoHub.dto.Category.CategoryDetailsDTO;
import JuanJose.ForoHub.dto.Response.DTOResponse;
import JuanJose.ForoHub.entities.Topic;
import JuanJose.ForoHub.entities.TopicStatus;
import JuanJose.ForoHub.entities.TopicType;
import JuanJose.ForoHub.utils.ConverterData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ResponseTopicDTO(
        Long id,
        String title,
        TopicType type,
        String message,
        LocalDateTime creationDate,
        TopicStatus status,
        Long authorId,
        List<DTOResponse> responses,
        CategoryDetailsDTO categoryDetailsDTO


) {
    public ResponseTopicDTO(Topic topic) {

        this(topic.getId(), topic.getTitle(), topic.getType(), topic.getMessage(),
                topic.getCreationDate(), topic.getStatus(), topic.getAuthor().getId(),
                topic.getResponses() != null ? topic.getResponses().stream().map(ConverterData::convertToDTOResponse)
                        .collect(Collectors.toList()) : null,
                (topic.getCourse() != null &&
                        topic.getCourse().getSubCategory() != null &&
                        topic.getCourse().getSubCategory().getCategory() != null)
                ? new CategoryDetailsDTO(
                        topic.getCourse().getSubCategory().getCategory().getId(),
                        topic.getCourse().getSubCategory().getCategory().getName(),
                        topic.getCourse().getSubCategory().getId(),
                        topic.getCourse().getSubCategory().getName(),
                        topic.getCourse().getId(),
                        topic.getCourse().getName() != null ? topic.getCourse().getName() : "off topic")
                        : new CategoryDetailsDTO(
                        null, // Puedes usar null o un valor por defecto
                        "off topic", // Por defecto si no hay curso
                        null,
                        "off topic", // Por defecto si no hay subcategoría
                        null,
                        "off topic" // Por defecto si no hay curso
                )
        );

    }
}
