package JuanJose.ForoHub.dto.Permission;

import JuanJose.ForoHub.model.Permission;

public record ResponsePermissionDTO(
        Long id,
        String name,
        String description
) {
    public ResponsePermissionDTO(Permission permission) {
        this(permission.getId(), permission.getName(), permission.getDescription());
    }
}
