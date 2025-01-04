package JuanJose.ForoHub.dto.User;

import JuanJose.ForoHub.model.ForumUser;
import JuanJose.ForoHub.model.Profile;

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
