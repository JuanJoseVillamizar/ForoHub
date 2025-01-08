package JuanJose.ForoHub.dto.User;

import JuanJose.ForoHub.entities.ForumUser;

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
