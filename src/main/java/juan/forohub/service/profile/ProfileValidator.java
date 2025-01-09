package juan.forohub.service.profile;

import juan.forohub.exception.ResourceAlreadyExistException;
import juan.forohub.entities.Profile;
import juan.forohub.repository.ProfileRepository;
import juan.forohub.service.validations.AbstractEntityValidator;
import org.springframework.stereotype.Component;

@Component
public class ProfileValidator extends AbstractEntityValidator<Profile> {

    private final ProfileRepository profileRepository;

    public ProfileValidator(ProfileRepository profileRepository) {
        super(profileRepository);
        this.profileRepository = profileRepository;
    }

    public void validateProfileExistsByName(String name) {
        if (profileRepository.existsByName(name)) {
            throw new ResourceAlreadyExistException("A profile with the name " + name + " already exists.");
        }
    }
}
