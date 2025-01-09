package juan.forohub.dto.user;

import juan.forohub.entities.ForumUser;

public record ResponseUserDTO(
        Long id,
        String name,
        String email,
        Long profileId
) {
    public ResponseUserDTO (ForumUser user){
        this(user.getId(),user.getName(),user.getEmail(),user.getProfile().getId());
    }
}
