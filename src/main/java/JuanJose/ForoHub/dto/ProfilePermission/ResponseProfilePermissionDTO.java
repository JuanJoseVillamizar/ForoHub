package JuanJose.ForoHub.dto.ProfilePermission;

import JuanJose.ForoHub.entities.ProfilePermission;

public record ResponseProfilePermissionDTO(
        String profile,
        String permission
) {
    public ResponseProfilePermissionDTO(ProfilePermission profilePermission){
        this(profilePermission.getProfile().getName(),profilePermission.getPermission().getName());
    }
}

