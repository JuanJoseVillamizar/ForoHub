package juan.forohub.dto.profilePermission;

import juan.forohub.entities.ProfilePermission;

public record ResponseProfilePermissionDTO(
        String profile,
        String permission
) {
    public ResponseProfilePermissionDTO(ProfilePermission profilePermission){
        this(profilePermission.getProfile().getName(),profilePermission.getPermission().getName());
    }
}

