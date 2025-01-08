package JuanJose.ForoHub.dto.User;

import JuanJose.ForoHub.entities.ForumUser;

public record DetailsResponseUserDTO(
        Long id,
        String name,
        String email,
        String profile
) {
    public DetailsResponseUserDTO (ForumUser user){
        this(user.getId(), user.getName(), user.getEmail(), user.getProfile().getName());
    }
}
