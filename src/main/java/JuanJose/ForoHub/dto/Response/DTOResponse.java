package JuanJose.ForoHub.dto.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DTOResponse(
        Long id,
        String message,
        boolean isSolution,
        LocalDateTime creationDate,
        Long authorId
) {
}
