package JuanJose.ForoHub.dto.Profile;

import JuanJose.ForoHub.dto.Permission.ResponsePermissionDTO;

import java.util.List;

public record ProfileDTO(
        Long id,
        String name,
        String description,
        List<ResponsePermissionDTO> permissionDTO
) {
}
