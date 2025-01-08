package JuanJose.ForoHub.dto.Permission;

import JuanJose.ForoHub.entities.Permission;

public record DetailsResponsePermissionDTO(
        Long id,
        String name,
        String description
) {
    public DetailsResponsePermissionDTO (Permission permission){
        this(permission.getId(), permission.getName(), permission.getDescription());
    }
}
