package juan.forohub.dto.response;

import juan.forohub.entities.Response;

import java.time.LocalDateTime;

public record DetailsResponseDTO(
        Long id,
        String message,
        LocalDateTime creationDate,
        String topic,
        String author
) {
    public DetailsResponseDTO (Response response){
        this(response.getId(), response.getMessage(), response.getCreationDate(),response.getTopic().getTitle(),
                response.getAuthor().getName());
    }
}
