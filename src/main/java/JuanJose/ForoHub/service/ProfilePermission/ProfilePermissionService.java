package JuanJose.ForoHub.service.ProfilePermission;

import JuanJose.ForoHub.dto.ProfilePermission.CreateProfilePermissionDTO;
import JuanJose.ForoHub.dto.ProfilePermission.DetailsResponseProfilePermissionDTO;
import JuanJose.ForoHub.dto.ProfilePermission.ResponseProfilePermissionDTO;
import JuanJose.ForoHub.model.Permission;
import JuanJose.ForoHub.model.Profile;
import JuanJose.ForoHub.model.ProfilePermission;
import JuanJose.ForoHub.repository.PermissionRepository;
import JuanJose.ForoHub.repository.ProfilePermissionRepository;
import JuanJose.ForoHub.repository.ProfileRepository;
import JuanJose.ForoHub.service.Permission.PermissionValidator;
import JuanJose.ForoHub.service.Profile.ProfileValidator;
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
