package juan.forohub.dto.profile;

import juan.forohub.dto.permission.ResponsePermissionDTO;

import java.util.List;

public record ProfileDTO(
        Long id,
        String name,
        String description,
        List<ResponsePermissionDTO> permissionDTO
) {
}
