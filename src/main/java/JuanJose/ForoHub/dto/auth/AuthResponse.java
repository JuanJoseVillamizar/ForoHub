package JuanJose.ForoHub.dto.auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username", "message", "jwt", "status"})
public record AuthResponse(
        String username,
        String jwt,
        String message,
        boolean status
) {
}
