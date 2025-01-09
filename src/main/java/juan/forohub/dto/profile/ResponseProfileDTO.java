package juan.forohub.dto.profile;

import juan.forohub.entities.Profile;

public record ResponseProfileDTO(
        Long id,
        String name,
        String description
) {
    public ResponseProfileDTO (Profile profile){
        this(profile.getId(), profile.getName(), profile.getDescription());
    }
}
