package JuanJose.ForoHub.dto.User;

import JuanJose.ForoHub.dto.Permission.ResponsePermissionDTO;
import JuanJose.ForoHub.dto.Profile.ProfileDTO;
import JuanJose.ForoHub.dto.Profile.ResponseProfileDTO;
import JuanJose.ForoHub.model.ForumUser;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public record UserProfilePermissionDTO(
        Long id,
        String name,
        String email,
        ProfileDTO profile
) {
    public UserProfilePermissionDTO(ForumUser user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                new ProfileDTO(
                        user.getProfile().getId(),
                        user.getProfile().getName(),
                        user.getProfile().getDescription(),
                        user.getProfile().getPermissions().stream()
                                .map(permission ->
                                        new ResponsePermissionDTO(
                                                permission.getId(),
                                                permission.getName(),
                                                permission.getDescription()
                                        ))
                                .collect(Collectors.toList())
                )
        );
    }

}
