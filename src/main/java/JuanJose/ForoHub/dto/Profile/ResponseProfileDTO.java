package JuanJose.ForoHub.dto.Profile;

import JuanJose.ForoHub.model.Profile;

public record ResponseProfileDTO(
        Long id,
        String name,
        String description
) {
    public ResponseProfileDTO (Profile profile){
        this(profile.getId(), profile.getName(), profile.getDescription());
    }
}
