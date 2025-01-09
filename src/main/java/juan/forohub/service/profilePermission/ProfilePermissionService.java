package juan.forohub.service.profilePermission;

import juan.forohub.dto.profilePermission.CreateProfilePermissionDTO;
import juan.forohub.dto.profilePermission.DetailsResponseProfilePermissionDTO;
import juan.forohub.dto.profilePermission.ResponseProfilePermissionDTO;
import juan.forohub.entities.Permission;
import juan.forohub.entities.Profile;
import juan.forohub.entities.ProfilePermission;
import juan.forohub.repository.PermissionRepository;
import juan.forohub.repository.ProfilePermissionRepository;
import juan.forohub.repository.ProfileRepository;
import juan.forohub.service.permission.PermissionValidator;
import juan.forohub.service.profile.ProfileValidator;
import org.springframework.stereotype.Service;

@Service
public class ProfilePermissionService {

    private final ProfilePermissionRepository ppRepository;
    private final ProfileValidator profileValidator;
    private final PermissionValidator permissionValidator;
    private final ProfileRepository profileRepository;
    private final PermissionRepository permissionRepository;

    public ProfilePermissionService (ProfilePermissionRepository ppRepository, ProfileValidator profileValidator,
                                     PermissionValidator permissionValidator, ProfileRepository profileRepository,
                                     PermissionRepository permissionRepository){
        this.ppRepository=ppRepository;
        this.profileValidator=profileValidator;
        this.permissionValidator=permissionValidator;
        this.profileRepository=profileRepository;
        this.permissionRepository=permissionRepository;
    }

    public DetailsResponseProfilePermissionDTO addPermissions(CreateProfilePermissionDTO data) {
        profileValidator.validateExistsById(data.profileId());
        permissionValidator.validateExistsById(data.permissionId());
        Profile profile = profileRepository.getReferenceById(data.profileId());
        Permission permission = permissionRepository.getReferenceById(data.permissionId());
        if(ppRepository.existsByProfileAndPermission(profile,permission)){
            return new DetailsResponseProfilePermissionDTO(
                    "Permission is already assigned to this profile", null);
        }
        ProfilePermission profilePermission = new ProfilePermission(profile,permission);
        ppRepository.save(profilePermission);
        return new DetailsResponseProfilePermissionDTO(
                "Permission assigned successfully",toResponseProfilePermissionDTO(profilePermission));
    }

    // Converter to ResponseProfilePermissionDTO
    private ResponseProfilePermissionDTO toResponseProfilePermissionDTO(ProfilePermission profilePermission) {
        return new ResponseProfilePermissionDTO(
                profilePermission.getProfile().getName(),
                profilePermission.getPermission().getName());
    }
}
