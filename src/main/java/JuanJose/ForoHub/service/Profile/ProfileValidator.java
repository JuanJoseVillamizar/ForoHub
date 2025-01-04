package JuanJose.ForoHub.service.Profile;

import JuanJose.ForoHub.exception.ResourceAlreadyExistException;
import JuanJose.ForoHub.model.Profile;
import JuanJose.ForoHub.repository.ProfileRepository;
import JuanJose.ForoHub.service.Validations.AbstractEntityValidator;
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
