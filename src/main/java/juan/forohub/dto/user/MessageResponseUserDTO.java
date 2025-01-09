package juan.forohub.dto.user;

public record MessageResponseUserDTO(
        String message,
        DetailsResponseUserDTO user
) {
}
