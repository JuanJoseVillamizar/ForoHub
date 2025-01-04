package JuanJose.ForoHub.dto.Profile;

import JuanJose.ForoHub.model.Profile;

public record DetailsResponseProfileDTO (
        Long id,
        String name,
        String description
) {
    public DetailsResponseProfileDTO (Profile profile){
        this(profile.getId(),profile.getName(),profile.getDescription());
    }

}
