package JuanJose.ForoHub.dto.Response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateResponseDTO(
        @NotBlank
        String message,
        @NotNull
        Long topicId,
        @NotNull
        Long userId
) {
}
