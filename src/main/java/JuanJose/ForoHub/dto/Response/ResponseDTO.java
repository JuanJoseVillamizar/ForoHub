package JuanJose.ForoHub.dto.Response;

import JuanJose.ForoHub.entities.Response;

import java.time.LocalDateTime;

public record ResponseDTO(
        Long id,
        String message,
        LocalDateTime creationDate,
        Long topicId,
        Long authorId
) {
    public ResponseDTO (Response response){
        this(response.getId(),response.getMessage(),response.getCreationDate(),
                response.getTopic().getId(),response.getAuthor().getId());
    }
}
